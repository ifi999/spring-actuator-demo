package com.ifi.actuatordemo.timer;

import io.micrometer.core.instrument.FunctionTimer;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FunctionTimerConfig {

    @Bean
    public FunctionTimer myFunctionTimer(MeterRegistry meterRegistry, MyTimerManager myTimerManager) {
        return FunctionTimer.builder(
                "my.timer5"
                , myTimerManager
                , MyTimerManager::getCount
                , MyTimerManager::getTotalTime
                , TimeUnit.SECONDS
                )
                .register(meterRegistry);
    }

}
