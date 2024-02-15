package com.example.khaier.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HealthChecker implements HealthIndicator {

    @Override
    public Health health() {
        long freeMemory = Runtime.getRuntime().freeMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        double freeMemoryPercent = ((double) freeMemory / totalMemory) * 100;

        log.info("free_memory: {} bytes", freeMemory);
        log.info("total_memory: {} bytes", totalMemory);
        log.info("free_memory_percent: {}%", freeMemoryPercent);

        Health.Builder healthBuilder = (freeMemoryPercent > 30) ? Health.up() : Health.down();

        return healthBuilder
                .withDetail("free_memory", freeMemory + " bytes")
                .withDetail("total_memory", totalMemory + " bytes")
                .withDetail("free_memory_percent", freeMemoryPercent + " %")
                .build();
    }
}
