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
        long freeMemory=Runtime.getRuntime().freeMemory();
        long totalMemory= Runtime.getRuntime().totalMemory();
        double freeMemoryPercent=((double)freeMemory/(double) totalMemory)*100;
        if (freeMemoryPercent > 25){
            log.info("free_memory: "+freeMemory + " bytes");
            log.info("total_memory: "+totalMemory +" bytes");
            log.info("free_memory_percent: "+freeMemoryPercent+" %");
            return Health.up()
                    .withDetail("free_memory ",freeMemory +" bytes")
                    .withDetail("total_memory ",totalMemory +" bytes")
                    .withDetail("free_memory_percent ",freeMemoryPercent +" %")
                    .build();
        }else {
            log.info("free_memory: "+freeMemory + " bytes");
            log.info("total_memory: "+totalMemory +" bytes");
            log.info("free_memory_percent: "+freeMemoryPercent+" %");
            return Health.down()
                    .withDetail("free_memory ",freeMemory +" bytes")
                    .withDetail("total_memory ",totalMemory +" bytes")
                    .withDetail("free_memory_percent ",freeMemoryPercent +" %")
                    .build();


        }
    }
}