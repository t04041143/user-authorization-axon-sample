package org.multilinguals.example.infrastructure.config;

import org.axonframework.config.ConfigurationScopeAwareProvider;
import org.axonframework.deadline.SimpleDeadlineManager;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ApplicationConfig {
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(500);
        executor.initialize();
        return executor;
    }
}