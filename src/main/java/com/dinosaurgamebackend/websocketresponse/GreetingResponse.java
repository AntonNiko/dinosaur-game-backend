package com.dinosaurgamebackend.websocketresponse;

public class GreetingResponse {
    private String content;

    public GreetingResponse() {

    }

    public GreetingResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
