package com.velikanovdev.sportcenterplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EntityScan(basePackages = {"com.velikanovdev.sportcenterplatform.entity"})
@EnableJpaRepositories(basePackages="com.velikanovdev.sportcenterplatform.repository")
@SpringBootApplication
public class SportCenterPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportCenterPlatformApplication.class, args);
    }

}
