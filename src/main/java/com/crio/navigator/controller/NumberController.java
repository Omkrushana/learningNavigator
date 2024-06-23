package com.crio.navigator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crio.navigator.service.NumberApiService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/numbers")
public class NumberController {

    @Autowired
    private NumberApiService numberApiService;

    @GetMapping("/{number}")
    public Mono<ResponseEntity<String>> getNumberFact(@PathVariable("number") int number) {
        return numberApiService.getNumberFact(number)
                .map(fact -> ResponseEntity.ok().body(fact))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
