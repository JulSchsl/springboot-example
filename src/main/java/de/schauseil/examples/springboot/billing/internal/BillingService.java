package de.schauseil.examples.springboot.billing.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import de.schauseil.examples.springboot.billing.api.Invoice;
import de.schauseil.examples.springboot.billing.api.InvoiceCalculator;
import de.schauseil.examples.springboot.billing.internal.database.api.LineItem;
import de.schauseil.examples.springboot.billing.internal.database.api.ReadLineItems;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
class BillingService implements InvoiceCalculator {

    private final ReadLineItems readLineItems;

    @Override
    public Invoice calculateInvoice(Long userId, LocalDate fromDate, LocalDate toDate) {
        List<LineItem> items = readLineItems.getLineItemsForUser(userId, fromDate, toDate);
        double sum = items.stream()
                .mapToDouble(LineItem::getAmount)
                .sum();
        return new Invoice(userId, sum);
    }
}
