package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MealsDaoMemory implements MealsDao {

    private static final List<Meal> mealsData = new CopyOnWriteArrayList<>();

    public MealsDaoMemory() {
        if (mealsData.isEmpty()) {
            mealsData.addAll(MealsUtil.createFirstDataMeals());
        }
    }

    @Override
    public boolean create(Meal meal) {
        if (readOne(meal.getId()) == null) {
            mealsData.add(meal);
            return true;
        }
        return false;
    }

    @Override
    public Meal readOne(long id) {
        return mealsData.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public List<Meal> read() {
        return mealsData;
    }

    @Override
    public boolean update(Meal meal) {
        if (readOne(meal.getId()) != null) {
            Meal oldMeal = readOne(meal.getId());
            int index = mealsData.indexOf(oldMeal);
            mealsData.set(index, meal);
            create(meal);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        for (Meal meal: mealsData) {
            if (meal.getId() == id) {
                mealsData.remove(meal);
                return true;
            }
        }
        return false;
    }
}
