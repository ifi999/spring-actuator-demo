package com.ifi.actuatordemo.timer;

import io.micrometer.core.instrument.FunctionTimer;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class TimerConfigWithMeterBinder {

    @Bean
    public MeterBinder myTimerMeterBinder(MyTimerManager myTimerManager) {
        return meterRegistry -> FunctionTimer.builder(
                        "my.timer6"
                        , myTimerManager
                        , MyTimerManager::getCount
                        , MyTimerManager::getTotalTime
                        , TimeUnit.SECONDS
                )
                .register(meterRegistry);
    }

}
