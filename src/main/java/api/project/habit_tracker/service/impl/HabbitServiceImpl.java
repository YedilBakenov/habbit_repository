package api.project.habit_tracker.service.impl;

import api.project.habit_tracker.exception.HabbitException;
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
        return habbitRepository.findById(id).orElseThrow(()-> new HabbitException("NOT FOUND HABBIT"));
    }

    @Override
    public Habbit updateHabbit(long id, Habbit habbit) {

            habbit.setId(id);
            return habbitRepository.save(habbit);
    }

    @Override
    public Habbit saveHabbit(Habbit habbit) {
        return habbitRepository.save(habbit);
    }

    @Override
    public void deleteHabbitById(long id) {
        Habbit habbit = getHabbitById(id);
        habbitRepository.deleteById(id);
    }
}
