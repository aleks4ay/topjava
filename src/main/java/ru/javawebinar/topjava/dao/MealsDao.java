package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public interface MealsDao {

    void create(Meal meal);
    List<Meal> read();
    Meal readOne(long id);
    void update(Meal meal);
    void delete(long id);
}
