package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.UnicastProcessor;

// Structure of message sent over Chat message
// {"username": "magemello", "content": "my message"}

public class ChatSocketHandler implements WebSocketHandler {

    private final UnicastProcessor<String> eventPublisher = UnicastProcessor.create();

    private final Flux<String> outputEvents;

    public ChatSocketHandler() {
        this.outputEvents = eventPublisher.publish().autoConnect();
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        WebSocketMessageSubscriber webSocketMessageSubscriber =
                new WebSocketMessageSubscriber(eventPublisher);
        session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .subscribe(webSocketMessageSubscriber::onNext,
                        webSocketMessageSubscriber::onError,
                        webSocketMessageSubscriber::onComplete);
        return session.send(outputEvents.map(session::textMessage));
    }

    private static class WebSocketMessageSubscriber {

        private final UnicastProcessor<String> eventPublisher;

        private String username;

        public WebSocketMessageSubscriber(UnicastProcessor<String> eventPublisher) {
            this.eventPublisher = eventPublisher;
        }

        public void onNext(String message) {
            if (username == null) {
                JsonNode jsonEvent = null;
                try {
                    jsonEvent = toJson(message);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                this.username = jsonEvent.get("username").asText();
                systeJoinUserMessage();
            } else {
                eventPublisher.onNext(message);
            }
        }

        private void systeJoinUserMessage() {
            eventPublisher.onNext("{\"username\":\"system\",\"content\":\"" + username + " joined your channel\"}");
        }

        public void onComplete() {
            eventPublisher.onNext("{\"username\":\"system\",\"content\":\"" + username + " left your channel\"}");
        }

        public void onError(Throwable err) {
            err.printStackTrace();
        }

        private JsonNode toJson(String message) throws JsonProcessingException {
            String json = "{\"username\":\"" + username + "\",\"content\":\"" + message + "\"}";

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readTree(json);
        }

    }
}
