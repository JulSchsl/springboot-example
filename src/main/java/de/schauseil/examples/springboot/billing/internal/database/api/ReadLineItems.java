package de.schauseil.examples.springboot.billing.internal.database.api;

import java.time.LocalDate;
import java.util.List;

public interface ReadLineItems {

    List<LineItem> getLineItemsForUser(Long userId, LocalDate startDate, LocalDate endDate);

}
