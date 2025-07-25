package com.example.account.logging;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggingRepository extends JpaRepository<RabbitLog, Long> {

}
