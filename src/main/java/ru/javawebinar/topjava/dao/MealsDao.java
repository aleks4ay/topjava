package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public interface MealsDao {
//    List<Meal> mealsData = new CopyOnWriteArrayList<>();

    boolean create(Meal meal);
    List<Meal> read();
    Meal readOne(long id);
    boolean update(Meal meal);
    boolean delete(long id);

//    List<Meal> getMealsData();
}
