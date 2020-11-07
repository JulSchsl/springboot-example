package de.schauseil.examples.springboot.billing.internal.database.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import de.schauseil.examples.springboot.billing.internal.database.api.LineItem;
import de.schauseil.examples.springboot.billing.internal.database.api.ReadLineItems;
import de.schauseil.examples.springboot.billing.internal.database.api.WriteLineItems;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class BillingDatabase implements WriteLineItems, ReadLineItems {

    private final LineItemRepository lineItemRepository;

    @Override
    public void saveLineItems(List<LineItem> lineItems) {
        for (LineItem lineItem : lineItems) {
            lineItemRepository.save(LineItemJpaEntity.fromDomainObject(lineItem));
        }
    }

    @Override
    public List<LineItem> getLineItemsForUser(Long userId, LocalDate startDate, LocalDate endDate) {
        return lineItemRepository.findByUserIdAndDateBetween(userId, startDate, endDate).stream()
                .map(LineItemJpaEntity::toDomainObject)
                .collect(Collectors.toList());
    }
}
