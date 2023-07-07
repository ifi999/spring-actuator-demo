package com.ifi.actuatordemo.tags;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tag")
@Slf4j
public class TagController {

    private final MyQueueManagerWithTags myManager;
    private final MeterRegistry meterRegistry;

    public TagController(MyQueueManagerWithTags myManager, MeterRegistry meterRegistry) {
        this.myManager = myManager;
        this.meterRegistry = meterRegistry;
    }

    //    @Counted(value = "my.counted", extraTags = { "type1", "value1", "type2", "value2" })
    @GetMapping("/push")
    public String push() {
        myManager.push();
        return "ok";
    }

//    @Counted(value = "my.counted", extraTags = { "type3", "value3", "type4", "value4" })
    @GetMapping("/pop")
    public String pop() {
        myManager.pop();
        return "ok";
    }

    @GetMapping("/test")
    public String test() {
        Counter count1 = Counter.builder("my.test")
                .tag("type", "push")
                .register(meterRegistry);

        Counter count2 = Counter.builder("my.test")
                .tag("type", "push")
                .register(meterRegistry);

        Counter count3 = Counter.builder("my.test")
                .tag("type", "push2")
                .register(meterRegistry);

        Counter count4 = Counter.builder("my.test")
                .tag("type", "push3")
                .register(meterRegistry);

        log.info("{}, {}, {}, {}", count1, count2, count3, count4);

        return "ok";
    }

}
