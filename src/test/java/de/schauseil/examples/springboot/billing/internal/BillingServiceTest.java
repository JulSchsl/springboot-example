package de.schauseil.examples.springboot.billing.internal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.schauseil.examples.springboot.billing.api.Invoice;
import de.schauseil.examples.springboot.billing.internal.database.api.LineItem;
import de.schauseil.examples.springboot.billing.internal.database.api.ReadLineItems;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class BillingServiceTest {

    private final ReadLineItems readLineItemsMock;
    private final BillingService billingService;

    BillingServiceTest() {
        this.readLineItemsMock = Mockito.mock(ReadLineItems.class);
        this.billingService = new BillingService(readLineItemsMock);
    }

    @Test
    void calculateInvoice_givenLineItems_computesCorrectInvoice() {
        long userId = 42L;
        LocalDate now = LocalDate.now();
        LineItem lineItem1 = new LineItem(userId, "cucumber", 2, now);
        LineItem lineItem2 = new LineItem(userId, "banana", 1, now);

        when(readLineItemsMock.getLineItemsForUser(anyLong(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(List.of(lineItem1, lineItem2));

        Invoice invoice = billingService.calculateInvoice(userId, now, now);
        assertEquals(3, invoice.getAmount());
        assertEquals(userId, invoice.getUserId());
    }
}