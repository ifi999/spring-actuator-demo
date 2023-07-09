package com.ifi.actuatordemo.timer;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timer")
public class TimerController {

    private final MeterRegistry meterRegistry;

    @Qualifier("myTimer")
    private final Timer myTimer;

    public TimerController(MeterRegistry meterRegistry, Timer myTimer) {
        this.meterRegistry = meterRegistry;
        this.myTimer = myTimer;
    }

    @GetMapping("/timer")
    public String timer() {
        myTimer.record(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return "ok";
    }

    @GetMapping("/timer2")
    public String timer2() throws InterruptedException {
        Timer.Sample sample = Timer.start(meterRegistry);

        // 비즈니스 로직
        Thread.sleep(2000);

        test(sample);

        return "ok";
    }

    @Timed("my.timer3")
    @GetMapping("/timer3/{sleepSeconds}")
    public String timer3(@PathVariable("sleepSeconds") int sleepSeconds) throws InterruptedException {
        Thread.sleep(sleepSeconds *1000);

        return "ok";
    }

    private void test(Timer.Sample sample) {
//        if ( ~~~ )
        sample.stop(meterRegistry.timer("my.timer2"));
//        else
    }

}
