package com.robin.statemachine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

/**
 * 持久化
 */
@Configuration
public class PersistHandlerConfig<States, Events, String> {

    @Autowired
    private InMemoryStateMachinePersist inMemoryStateMachinePersist;

    @Bean
    public StateMachinePersister<States, Events, String> getPersister() {
        return new DefaultStateMachinePersister(inMemoryStateMachinePersist);
    }
}
