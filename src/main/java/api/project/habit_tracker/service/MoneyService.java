package api.project.habit_tracker.service;

import api.project.habit_tracker.model.Money;
import api.project.habit_tracker.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MoneyService {

    List<Money> getAllMoney();
}
