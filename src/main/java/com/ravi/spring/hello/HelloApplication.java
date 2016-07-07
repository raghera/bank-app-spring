/**
 * 
 */
package com.ravi.spring.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Simple Spring DI test to check it's working ok
 * 
 * @author Ravi Aghera
 *
 */

@Configuration
@ComponentScan
public class HelloApplication {

	/**
	 * returns a MessageService bean
	 * @return
	 */
	@Bean
	MessageService mockMessageService() {
		return new MessageService() {
			@Override
			public String getMessage() {
				return "Hello World1";
			}
		};
	}
	
	//Since it's a standalone application - you need to explicitly load the context
	public static void main(String [] args) {
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(HelloApplication.class);
		
        MessagePrinter printer = context.getBean(MessagePrinter.class);
        printer.printMessage();		
	}
	
}
