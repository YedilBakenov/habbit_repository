package api.project.habit_tracker.service.impl;

import api.project.habit_tracker.exeption.HabbitExeption;
import api.project.habit_tracker.model.Habbit;
import api.project.habit_tracker.repository.HabbitRepository;
import api.project.habit_tracker.service.HabbitService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabbitServiceImpl implements HabbitService {

    private final HabbitRepository habbitRepository;
    private final EntityManager entityManager;

    @Override
    public List<Habbit> getAllHabbits(String name, Double duration) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Habbit> query = cb.createQuery(Habbit.class);
        Root<Habbit> root = query.from(Habbit.class);

        List<Predicate> predicates = new ArrayList<>();

        if(name!=null && !name.isEmpty()){
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + name + "%"));
        }

        if(duration!=null){
            predicates.add(cb.equal(root.get("duration"), duration));
        }

        query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public Habbit getHabbitById(long id) {
        return habbitRepository.findById(id).orElseThrow();
    }

    @Override
    public Habbit updateHabbit(long id, Habbit habbit) {

        Habbit habbit1 = getHabbitById(id);

        if(habbit1!=null){
            habbit.setId(habbit1.getId());
            habbitRepository.save(habbit);
            return habbit;
        }
        return null;
    }

    @Override
    public Habbit saveHabbit(Habbit habbit) {
        return habbitRepository.save(habbit);
    }

    @Override
    public void deleteHabbitById(long id) {
        habbitRepository.deleteById(id);
    }
}
