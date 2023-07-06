package com.ifi.actuatordemo.counter;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterConfigWithMeterBinder {

    @Bean
    public MeterBinder myCounterWithMeterBinder(MyHttpRequestManagerWithoutMicrometer myManager) {
        return meterRegistry -> FunctionCounter.builder("my.function.counter", myManager, MyHttpRequestManagerWithoutMicrometer::getCount)
                .register(meterRegistry);
    }

}
