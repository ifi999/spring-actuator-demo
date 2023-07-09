package com.ifi.actuatordemo.gauge;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// GaugeConfig 와 둘 중 하나만 @Configuration 활성
@Configuration
public class GaugeConfigWithMeterBinder {

    @Bean
    public MeterBinder gaugeMeterBinder(QueueManager queueManager) {
        return meterRegistry -> {
            Gauge.builder("my.queue.size", queueManager, QueueManager::getQueueSize)
                    .register(meterRegistry);
        };
    }

}
