package api.project.habit_tracker.service;

import api.project.habit_tracker.model.Habbit;
import api.project.habit_tracker.model.User;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public interface HabbitService {

    List<Habbit> getAllHabbits(String name, Double duration);

    Habbit getHabbitById(long id);

    Habbit updateHabbit(long id, Habbit habbit);

    Habbit saveHabbit(Habbit habbit);

    void deleteHabbitById(long id);
}
