package com.robin.statemachine.services.action;

import com.alibaba.fastjson.JSONObject;
import com.robin.statemachine.events.Events;
import com.robin.statemachine.states.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Service;

@Service
public class Action2Service implements Action<States,Events> {

    @Override
    public void execute(StateContext<States, Events> stateContext) {
        System.out.println("当前方法 ActionService2");
        System.out.println("获得参数" + JSONObject.toJSONString(stateContext.getMessage()) + " Event:" + stateContext.getEvent());
        System.out.println("执行业务逻辑");
    }
}
