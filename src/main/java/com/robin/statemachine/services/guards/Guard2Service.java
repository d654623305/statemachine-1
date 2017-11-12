package com.robin.statemachine.services.guards;

import com.robin.statemachine.events.Events;
import com.robin.statemachine.states.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Service;

@Service
public class Guard2Service implements Guard<States,Events> {
    @Override
    public boolean evaluate(StateContext<States, Events> stateContext) {
        System.out.println("进入守护Service：Guard2");
        if (stateContext.getSource().getId().equals(States.STATES2)) {
            return Boolean.TRUE;
        }
        return false;
    }
}
