package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    @Autowired
    private CrudMealRepository crudMealRepository;
    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        }
        else {
            meal.setUser(crudUserRepository.findById(userId).orElse(null));
            crudMealRepository.save(meal);
            return meal;
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        if (get(id, userId) != null) {
            crudMealRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = crudMealRepository.findById(id).orElse(null);
        if (meal == null || meal.getUser().getId() != userId) {
            return null;
        }
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudMealRepository.getAllByUserId(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudMealRepository.findAllBetweenByUserId(startDateTime, endDateTime, userId);
    }
}
