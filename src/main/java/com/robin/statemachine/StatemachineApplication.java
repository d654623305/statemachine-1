package com.robin.statemachine;

import com.robin.statemachine.events.Events;
import com.robin.statemachine.states.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;

import java.util.Random;

@SpringBootApplication
public class StatemachineApplication implements CommandLineRunner{

	@Autowired
	StateMachine<States,Events> stateMachine;

	public static void main(String[] args) {
		SpringApplication.run(StatemachineApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		Message<Events> message1= MessageBuilder.withPayload(Events.EVENT1).setHeader("key",123).build();

		Message<Events>message2=MessageBuilder.withPayload(Events.EVENT2).setHeader("sign",234).build();

		stateMachine.sendEvent(message1);
		stateMachine.sendEvent(message2);


	}
}
