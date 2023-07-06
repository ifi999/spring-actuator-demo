package com.ifi.actuatordemo.counter;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFunctionCounterConfig {

    private final MyHttpRequestManagerWithoutMicrometer myManager;

    private final MeterRegistry meterRegistry;

    public MyFunctionCounterConfig(MyHttpRequestManagerWithoutMicrometer myManager, MeterRegistry meterRegistry) {
        this.myManager = myManager;
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    void init() {
        FunctionCounter.builder("my.function.counter", myManager, MyHttpRequestManagerWithoutMicrometer::getCount)
                .register(meterRegistry);
    }

}
