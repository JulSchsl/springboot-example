package de.schauseil.examples.springboot.billing.internal.batchjob.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.schauseil.examples.springboot.billing.internal.database.api.LineItem;
import de.schauseil.examples.springboot.billing.internal.database.api.WriteLineItems;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Component
class LoadInvoiceDataBatchJob {

    private static final Random random = new Random();
    private final WriteLineItems writeLineItems;

    @Scheduled(fixedRate = 5000)
    void loadDataFromBillingSystem() {
        List<LineItem> items = getLineItemsFromBillingSystem();
        writeLineItems.saveLineItems(items);
    }

    private List<LineItem> getLineItemsFromBillingSystem() {
        // imagine this list is loaded from a 3rd party system
        return Arrays.asList(
                lineItem("bread"),
                lineItem("butter"),
                lineItem("toilet paper")
        );
    }

    private LineItem lineItem(String itemName) {
        return new LineItem(42L, itemName, 42.42d, LocalDate.now().minusDays(random.nextInt(10)));
    }

}
