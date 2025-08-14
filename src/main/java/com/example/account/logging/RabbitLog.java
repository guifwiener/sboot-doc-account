package com.example.account.logging;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Class to be saved on log repository
 */
@Data
@Entity
@Table(name = "rabbitmq_logs")
public class RabbitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String queue;

    private LocalDateTime logDate;

    private String message;

    private RabbitLog(String queue, String message) {
        this.queue = queue;
        this.message = message;
        this.logDate = LocalDateTime.now();
    }

    public static RabbitLog create(String queue, String message) {
        return new RabbitLog(queue, message);
    }

}
