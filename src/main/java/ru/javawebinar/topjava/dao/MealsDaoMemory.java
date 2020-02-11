package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MealsDaoMemory implements MealsDao {

    private static List<Meal> mealsData = new CopyOnWriteArrayList<>();

    public MealsDaoMemory() {
        if (mealsData.isEmpty()) {
            mealsData.addAll(MealsUtil.createFirstDataMeals());
        }
    }

    @Override
    public void create(Meal meal) {
            mealsData.add(meal);
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
    public void update(Meal meal) {
            Meal oldMeal = readOne(meal.getId());
            int index = mealsData.indexOf(oldMeal);
            mealsData.set(index, meal);
    }

    @Override
    public void delete(long id) {
        for (Meal meal: mealsData) {
            if (meal.getId() == id) {
                mealsData.remove(meal);
                break;
            }
        }
    }
}
