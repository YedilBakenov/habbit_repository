package api.project.habit_tracker.repository;

import api.project.habit_tracker.model.Money;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MoneyRepository extends JpaRepository<Money, Long> {
}
