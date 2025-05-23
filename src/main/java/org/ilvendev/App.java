package org.ilvendev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Rework all list in API to use Optional (As we might not get anything in them) and then use ResponseEntity.of() in controller. But first find out if this works correctly

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}