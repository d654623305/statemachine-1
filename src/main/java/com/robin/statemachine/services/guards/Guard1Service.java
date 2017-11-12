package com.robin.statemachine.services.guards;

import com.robin.statemachine.states.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Service;

@Service
public class Guard1Service implements Guard{

    @Override
    public boolean evaluate(StateContext stateContext) {
        System.out.println("进入守护Service：Guard1");
        if (stateContext.getSource().getId().equals(States.STATES1)) {
            return Boolean.TRUE;
        }
        return false;
    }
}
