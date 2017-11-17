package com.robin.statemachine.config;

import com.robin.statemachine.events.Events;
import com.robin.statemachine.states.States;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by jinyu.meng on 2017/11/16.
 */
@Component
public class InMemoryStateMachinePersist implements StateMachinePersist<States, Events, String> {

    private static HashMap<String, StateMachineContext<States, Events>> map = new HashMap<>();

    @Override
    public void write(StateMachineContext<States, Events> context, String contextObj) throws Exception {
        map.put(contextObj, context);
    }


    @Override
    public StateMachineContext<States, Events> read(String contextObj) throws Exception {
        return map.get(contextObj);
    }
}
