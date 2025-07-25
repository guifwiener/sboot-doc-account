package com.example.account.connections;

import com.example.account.logging.LoggingRepository;
import com.example.account.logging.RabbitLog;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMQConfiguration {

    @Autowired
    LoggingRepository loggingRepository;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.virtualhost}")
    private String virtualhost;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    private static final String QUEUE_NAME = "DOC";

    @RabbitListener(queues = QUEUE_NAME)
    public void consume(String message) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(this.host);
        factory.setVirtualHost(this.virtualhost);
        factory.setPort(this.port);
        factory.setUsername(this.username);
        factory.setPassword(this.password);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        loggingRepository.save(RabbitLog.create(QUEUE_NAME, "Listener started"));
        System.out.println("Listener started");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String deliveryMessage = new String(delivery.getBody(), "UTF-8");
            loggingRepository.save(RabbitLog.create(QUEUE_NAME, " Received '" + deliveryMessage + "'"));
            System.out.println(" Received '" + deliveryMessage + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
