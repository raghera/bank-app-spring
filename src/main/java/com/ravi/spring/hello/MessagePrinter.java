/**
 * 
 */
package com.ravi.spring.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ravi Aghera
 *
 */
@Component
public class MessagePrinter {

	private MessageService messageService;
	
	@Autowired
	public MessagePrinter(MessageService service) {
		this.messageService = service;
	}

    public void printMessage() {
        System.out.println(this.messageService.getMessage());
    }
	
}
