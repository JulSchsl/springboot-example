package de.schauseil.examples.springboot.billing.internal.database.internal;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

interface LineItemRepository extends CrudRepository<LineItemJpaEntity, Long> {

    List<LineItemJpaEntity> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

}
