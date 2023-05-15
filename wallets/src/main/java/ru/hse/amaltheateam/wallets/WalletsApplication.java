package ru.hse.amaltheateam.wallets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WalletsApplication {
    public static void main(String[] args) {
        SpringApplication.run(WalletsApplication.class, args);
    }
}
