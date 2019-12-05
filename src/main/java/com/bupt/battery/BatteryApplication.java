package com.bupt.battery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BatteryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatteryApplication.class, args);
    }

}
