package de.schauseil.examples.springboot.billing.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.schauseil.examples.springboot.billing.api.Invoice;
import de.schauseil.examples.springboot.billing.api.InvoiceCalculator;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
class BillingController {

    private final InvoiceCalculator invoiceCalculator;

    @GetMapping("/invoice/lastweek")
    public Invoice invoiceLastWeek(@RequestParam(value = "userId", defaultValue = "42") Long userId) {
        LocalDate now = LocalDate.now();
        return invoiceCalculator.calculateInvoice(userId, now.minusDays(7).atStartOfDay().toLocalDate(), now);
    }
}
