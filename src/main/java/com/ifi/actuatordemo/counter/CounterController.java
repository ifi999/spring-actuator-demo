package com.ifi.actuatordemo.counter;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counter")
public class CounterController {

    private final MyHttpRequestManager myHttpRequestManager;

    private final MyHttpRequestManagerWithoutMicrometer myManager;

    private final MeterRegistry meterRegistry;

    public CounterController(MyHttpRequestManager myHttpRequestManager, MyHttpRequestManagerWithoutMicrometer myManager, MeterRegistry meterRegistry) {
        this.myHttpRequestManager = myHttpRequestManager;
        this.myManager = myManager;
        this.meterRegistry = meterRegistry;
    }

    @Counted("my.counted.counter")
    @GetMapping("/req")
    public String req() {
//        Counter.builder("my.http.request")
//                .register(meterRegistry)
//                .increment();

        return "ok";
    }

}
