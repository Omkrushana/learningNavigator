package com.crio.navigator.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NumberApiService {

    private final WebClient webClient;

    public NumberApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://numbersapi.com").build();
    }

    public Mono<String> getNumberFact(int number) {
        return this.webClient.get()
                .uri("/{number}", number)
                .retrieve()
                .bodyToMono(String.class);
    }
}
