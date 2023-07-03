package com.ifi.actuatordemo.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyCustomHealthIndicator implements HealthIndicator {

    boolean status = getStatus();

    @Override
    public Health health() {

        if (status) {
            Health health = Health.up()
                    .withDetail("key1", "value1")
                    .withDetail("key2", "value2")
                    .build();
            return health;
        }

        Health health = Health.down()
                .withDetail("key3", "value3")
                .withDetail("key4", "value4")
                .build();
        return health;
    }

    boolean getStatus() {
        return System.currentTimeMillis() % 2 == 0;
    }

}
