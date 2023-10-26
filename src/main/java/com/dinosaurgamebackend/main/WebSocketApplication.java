package com.dinosaurgamebackend.main;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.dinosaurgamebackend.game.Game;
import com.dinosaurgamebackend.websocketmessage.ConnectMessage;
import com.dinosaurgamebackend.websocketmessage.HelloMessage;
import com.dinosaurgamebackend.websocketresponse.GreetingResponse;

@Controller
public class WebSocketApplication {
	private final Game game = new Game();

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
    public GreetingResponse hello(HelloMessage helloMessage) throws Exception {
		Thread.sleep(1000);
		return new GreetingResponse("Hello, " + HtmlUtils.htmlEscape(helloMessage.getName()));
    }

	@MessageMapping("/connect-to-game")
	@SendTo("/topic/connect-to-game-response")
    public GreetingResponse connectToGame(ConnectMessage connectMessage) throws Exception {
		Thread.sleep(1000);
		return new GreetingResponse("Connect to game response");
    }


}
