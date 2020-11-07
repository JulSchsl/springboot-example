package de.schauseil.examples.springboot.billing.internal.database.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import de.schauseil.examples.springboot.billing.internal.database.api.LineItem;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ComponentScan // scan components in the package of this class
class BillingDatabaseTest {

    private final BillingDatabase billingDatabase;

    @Autowired
    BillingDatabaseTest(BillingDatabase billingDatabase) {
        this.billingDatabase = billingDatabase;
    }

    @Test
    void saveLineItemsThenGetLineItemsForUser_withValidLineItem_containsLineItem() {
        long userId = 4242L;
        LocalDate now = LocalDate.now();
        LineItem lineItem = new LineItem(userId, "cucumber", 2, now);
        billingDatabase.saveLineItems(List.of(lineItem));
        List<LineItem> lineItemsForUser = billingDatabase.getLineItemsForUser(userId, now, now);
        assertTrue(lineItemsForUser.contains(lineItem));
    }
}