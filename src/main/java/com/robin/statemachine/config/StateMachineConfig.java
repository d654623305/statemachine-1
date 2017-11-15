package com.robin.statemachine.config;

import com.robin.statemachine.events.Events;
import com.robin.statemachine.services.action.Action1Service;
import com.robin.statemachine.services.action.Action2Service;
import com.robin.statemachine.services.erroraction.ErrorAction1Service;
import com.robin.statemachine.services.erroraction.ErrorAction2Service;
import com.robin.statemachine.services.guards.Guard1Service;
import com.robin.statemachine.services.guards.Guard2Service;
import com.robin.statemachine.states.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Actions;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;


/***
 * state machine 基本配置
 */
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Autowired
    Action1Service action1Service;
    @Autowired
    Action2Service action2Service;
    @Autowired
    ErrorAction1Service errorAction1Service;
    @Autowired
    ErrorAction2Service errorAction2Service;
    @Autowired
    Guard1Service guard1Service;
    @Autowired
    Guard2Service guard2Service;


    /**
     * 全局配置
     *
     * @param config
     * @throws Exception
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer config) throws Exception {
        config.withConfiguration().autoStartup(Boolean.TRUE).
                listener(listener());
        super.configure(config);
    }

    /**
     * 状态配置
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer states) throws Exception {
        states.withStates().
                initial(States.STATES1).

                states(EnumSet.allOf(States.class)).

                end(States.STATES4);

        /**
         * 匹配多个statue
         */

        super.configure(states);
    }

    /**
     * 转换配置
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal().source(States.STATES1).target(States.STATES2).event(Events.EVENT1).
                action(action1Service, errorAction1Service).
                guard(guard1Service);

        transitions
                .withExternal().source(States.STATES2).target(States.STATES3).event(Events.EVENT2).
                action(Actions.errorCallingAction(action2Service, errorAction2Service)).
                guard(guard2Service);

        ;

        super.configure(transitions);
    }


    @Bean
    StateMachineListener listener() {

        /**
         * 监听全局  具体应用具体选择
         */
        StateMachineListener listener = new StateMachineListenerAdapter() {

            @Override
            public void stateChanged(State from, State to) {
                super.stateChanged(from, to);
            }

            @Override
            public void stateEntered(State state) {
                super.stateEntered(state);
            }

            @Override
            public void stateExited(State state) {
                super.stateExited(state);
            }

            @Override
            public void eventNotAccepted(Message event) {
                super.eventNotAccepted(event);
            }

            @Override
            public void transition(Transition transition) {
                super.transition(transition);
            }

            @Override
            public void transitionStarted(Transition transition) {
                super.transitionStarted(transition);
            }

            @Override
            public void transitionEnded(Transition transition) {
                super.transitionEnded(transition);
            }

            @Override
            public void stateMachineStarted(StateMachine stateMachine) {
                super.stateMachineStarted(stateMachine);
            }

            @Override
            public void stateMachineStopped(StateMachine stateMachine) {
                super.stateMachineStopped(stateMachine);
            }

            @Override
            public void stateMachineError(StateMachine stateMachine, Exception exception) {
                super.stateMachineError(stateMachine, exception);
            }

            @Override
            public void extendedStateChanged(Object key, Object value) {
                super.extendedStateChanged(key, value);
            }

            @Override
            public void stateContext(StateContext stateContext) {
                super.stateContext(stateContext);
            }
        };
        return listener;
    }


}
