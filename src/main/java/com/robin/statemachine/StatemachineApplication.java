package com.robin.statemachine;

import com.robin.statemachine.events.Events;
import com.robin.statemachine.states.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class StatemachineApplication implements CommandLineRunner {

    //	@Autowired
   //	StateMachine<States,Events> stateMachine;
    @Autowired
    StateMachineFactory<States, Events> stateMachineFactory;

    @Autowired

    public static void main(String[] args) {
        SpringApplication.run(StatemachineApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        StateMachine stateMachine = null;
        stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.start();
        Message<Events> message1 = MessageBuilder.withPayload(Events.EVENT1).setHeader("key", 123).build();

        Message<Events> message2 = MessageBuilder.withPayload(Events.EVENT2).setHeader("sign", 234).build();

        stateMachine.sendEvent(message1);
        stateMachine.sendEvent(message2);
        if (stateMachine.isComplete()) {
            stateMachine.stop();
        }
        test1();
    }

    /****
     * 该测试例子测试将状态机融入到实际项目中去
     * 状态机执行的条件 当前的状态，触发的事件 预期达到的结果
     * 测试中处理 如何获取当前状态的问题
     *
     * 服务器不停止的时候无任何问题
     *
     * 服务器停止在接上的时候如何保证获取的状态机的当前的状态？
     */
    void test1() {
        /**
         * 1:当前事件未知
         * 2:当前参数未知   参数和状态机的uuid or id 做一个转化关系
         *
         */

        for(int i=0;i<50;i++){
            Integer paramKey = new Random().nextInt(10);

            Message<Events> message = MessageBuilder.withPayload(result.get(new Random().nextInt(2))).setHeader("key", paramKey).build();

            StateMachine stateMachine = null;
            if (stateContext.get("machineParamsKey" + paramKey) != null) {

                stateMachine=stateContext.get("machineParamsKey" + paramKey);


            } else {
                stateMachine = stateMachineFactory.getStateMachine();
                stateMachine.start();
                stateContext.put("machineParamsKey"+paramKey,stateMachine);
            }
            Boolean result = stateMachine.sendEvent(message);
            if (result){
                System.out.println("当前"+stateMachine.getUuid().toString() +"---"+stateMachine.getState().getId() );
            }
        }

        System.out.println("本次测试结束，预期结果 相当一部分日志输出状态states3");

    }

    Map<String, StateMachine> stateContext = new HashMap<>();


    static Map<Integer, Events> result = new HashMap<>();

    static {
        result.put(0, Events.EVENT2);
        result.put(1, Events.EVENT1);
        result.put(2, Events.EVENT2);
        result.put(3, Events.EVENT3);
        result.put(4, Events.EVENT4);
    }


}
