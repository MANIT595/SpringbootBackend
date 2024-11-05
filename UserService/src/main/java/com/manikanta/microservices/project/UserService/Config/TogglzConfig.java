package com.manikanta.microservices.project.UserService.Config;

import com.manikanta.microservices.project.UserService.Enums.MyFeatures;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.repository.mem.InMemoryStateRepository;
import org.togglz.core.user.NoOpUserProvider;

@Configuration
public class TogglzConfig {

    @Bean
    public FeatureManager featureManager() {
        return new FeatureManagerBuilder()
                .featureEnum(MyFeatures.class)
                .stateRepository(new InMemoryStateRepository())  // InMemory for demo purposes
                .userProvider(new NoOpUserProvider())  // Replace with actual user provider for more complex setups
                .build();
    }
}
