package com.aforo255.appaccount.messages;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class IncreaseCounterAccount {
    
    private final Counter accountGetCounter;

    // account.get -> account_get_total
    public IncreaseCounterAccount(MeterRegistry meterRegistry) {
        accountGetCounter = meterRegistry.counter("account.get");
    }

    public void increaseCounter() {
        accountGetCounter.increment();
    }
}
