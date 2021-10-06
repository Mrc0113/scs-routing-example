package com.solace.samples.spring.scs;

import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

@SpringBootApplication
public class CloudStreamFunctionRouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStreamFunctionRouterApplication.class,
				"--spring.cloud.function.routing-expression="
						+ "headers['solace_correlationId'] == 'cons1' ? 'consumer1' : 'consumer2'",
				"--spring.cloud.stream.bindings.functionRouter-in-0.destination=my/solace/topic");
	}

	@Bean
	public Consumer<Message<String>> consumer1() {
		return value -> {
			System.out.println("Consumer1: " + value);
		};
	}

	@Bean
	public Consumer<Message<String>> consumer2() {
		return value -> {
			System.out.println("Consumer2: " + value);
		};
	}

}
