package me.batchtest.demobatchtest;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class DemoBatchtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBatchtestApplication.class, args);
    }

}
