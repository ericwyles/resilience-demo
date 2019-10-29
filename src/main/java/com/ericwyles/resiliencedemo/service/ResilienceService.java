package com.ericwyles.resiliencedemo.service;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@Slf4j
public class ResilienceService {

    UnreliableService unreliableService;

    @Autowired
    public ResilienceService(UnreliableService unreliableService) {
        this.unreliableService = unreliableService;
    }

    public String success() {
        log.info("Calling UnreliableService::success");
        return unreliableService.happy();
    }

    public String exceptionNaive() {
        log.info("Calling UnreliableService::exception");
        return unreliableService.exception();
    }


    public String exceptionWithCircuitBreaker() {
        log.info("configuring circuit breaker");

        // Create a CircuitBreaker with default confifugration
        CircuitBreaker circuitBreaker = CircuitBreaker
                .ofDefaults("unreliableService");

        // Create a Retry with default configuration
        // 3 retry attempts and a fixed time interval between retries of 500ms
        Retry retry = Retry
                .ofDefaults("unreliableService");

        Supplier<String> supplier = () -> unreliableService
                .exception();

        log.info("Calling UnreliableService::exception through resiliance4j decorated supplier");

        // Decorate your call with a CircuitBreaker and Retry
        Supplier<String> decoratedSupplier = Decorators.ofSupplier(supplier)
                .withRetry(retry)
                .withCircuitBreaker(circuitBreaker)
                .decorate();

        // Execute the decorated supplier and recover from any exception
        return Try.ofSupplier(decoratedSupplier)
                .recover(throwable -> "This method went through fallback logic from recover method of circuit breaker").get();
    }
}
