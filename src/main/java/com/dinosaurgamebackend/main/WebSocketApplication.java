package com.dinosaurgamebackend.main;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketApplication {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
    public GreetingResponse hello(HelloMessage helloMessage) throws Exception {
		Thread.sleep(1000);
		return new GreetingResponse("Hello, " + HtmlUtils.htmlEscape(helloMessage.getName()));
    }
}
