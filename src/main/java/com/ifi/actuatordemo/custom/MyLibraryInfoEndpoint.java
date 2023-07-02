package com.ifi.actuatordemo.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;

@Slf4j
//@Endpoint(id = "myLibraryInfo")
@WebEndpoint(id = "myLibraryInfo")
public class MyLibraryInfoEndpoint {

    @WriteOperation
    public void changeSomething(String name, boolean enableSomething) {
        log.info("name : {}, enableSomething : {}", name, enableSomething);
    }

    @ReadOperation
    public String getPathVariable(@Selector(match = Selector.Match.ALL_REMAINING) String[] path) {
        log.info("path : {}", Arrays.asList(path));
        return "path : " + Arrays.asList(path);
    }

    @ReadOperation
    public List<LibraryInfo> getLibraryInfos(@Nullable String name, boolean includeVersion) {
        LibraryInfo libraryInfo = new LibraryInfo();
        libraryInfo.setName("logback");
        libraryInfo.setVersion("1.0.0");

        LibraryInfo libraryInfo2 = new LibraryInfo();
        libraryInfo2.setName("jackson");
        libraryInfo2.setVersion("1.0.0");

        List<LibraryInfo> libraryInfos = Arrays.asList(libraryInfo, libraryInfo2);

        if (name != null) {
            libraryInfos = libraryInfos.stream()
                    .filter(lib -> lib.getName().equals(name))
                    .toList();
        }

        if (!includeVersion) {
            libraryInfos = libraryInfos.stream()
                    .map(lib -> {
                        LibraryInfo tmp = new LibraryInfo();
                        tmp.setName(lib.getName());
                        return tmp;
                    })
                    .toList();
        }

        return libraryInfos;
    }

}
