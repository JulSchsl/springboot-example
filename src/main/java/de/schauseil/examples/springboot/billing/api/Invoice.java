package de.schauseil.examples.springboot.billing.api;

import lombok.Value;

@Value
public class Invoice {
    Long userId;
    double amount;
}
