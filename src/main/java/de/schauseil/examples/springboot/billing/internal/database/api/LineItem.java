package de.schauseil.examples.springboot.billing.internal.database.api;

import lombok.Value;

import java.time.LocalDate;

@Value
public class LineItem {
    Long userId;
    String name;
    double amount;
    LocalDate date;
}
