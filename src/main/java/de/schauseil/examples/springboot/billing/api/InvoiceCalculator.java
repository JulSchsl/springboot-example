package de.schauseil.examples.springboot.billing.api;

import java.time.LocalDate;

public interface InvoiceCalculator {

    Invoice calculateInvoice(Long userId, LocalDate fromDate, LocalDate toDate);
}
