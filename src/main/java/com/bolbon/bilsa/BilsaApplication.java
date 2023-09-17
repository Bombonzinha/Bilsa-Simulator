package com.bolbon.bilsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.bolbon.repositories")
@EntityScan(basePackages = "com.bolbon.entities")
@ComponentScan(basePackages = "com.bolbon.*")
public class BilsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilsaApplication.class, args);
    }

}
