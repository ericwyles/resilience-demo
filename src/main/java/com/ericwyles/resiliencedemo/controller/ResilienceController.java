package com.ericwyles.resiliencedemo.controller;


import com.ericwyles.resiliencedemo.service.ResilienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ResilienceController {

    ResilienceService resilienceService;

    @Autowired
    public ResilienceController(ResilienceService resilienceService) {
        this.resilienceService = resilienceService;
    }

    @GetMapping("/success")
    public String success() {
        return resilienceService.success();
    }

    @GetMapping("/exception-naive")
    public String exceptionNaive() {
        return resilienceService.exceptionNaive();
    }

    @GetMapping("/exception-with-circuit-breaker")
    public String exceptionWithCircuitBreaker() {
        return resilienceService.exceptionWithCircuitBreaker();
    }
}
