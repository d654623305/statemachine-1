package com.robin.statemachine.services.erroraction;

import com.robin.statemachine.events.Events;
import com.robin.statemachine.states.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Service;


@Service
public class ErrorAction2Service  implements Action<States,Events> {

    @Override
    public void execute(StateContext<States, Events> stateContext) {
        System.out.println("ErrorAction2Service 开始回滚数据");
    }
}
