package org.multilinguals.enterprise.cmrs.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@ComponentScan({"org.multilinguals.enterprise.cmrs"})
@EnableReactiveMongoRepositories(basePackages = {"org.multilinguals.enterprise.cmrs"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
