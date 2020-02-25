package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception{
        Meal meal = service.get(MEAL_ID, USER_ID);
        assertMatch(meal, MEAL);
    }

    @Test(expected = NotFoundException.class)
    public void getNotOwnMeal() throws Exception{
        service.get(MEAL_ID, USER_ID + 1);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = getNew();
        Meal created = service.create(newMeal, USER_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        service.delete(MEAL_ID_3, USER_ID);
        service.get(MEAL_ID_3, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotOwnMeal() throws Exception {
        service.delete(MEAL_ID_2, USER_ID + 1);
    }

    @Test
    public void update() throws Exception{
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(MEAL_ID, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotOwnMeal() throws Exception{
        Meal updated = getUpdated();
        int wrongUserId = USER_ID + 1;
        service.update(updated, wrongUserId);
        service.get(updated.getId(), wrongUserId);
    }

    @Test
    public void getAll() throws Exception{
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MEAL_3, MEAL_2, MEAL);
    }

//    @Test
//    public void getBetweenHalfOpen() throws Exception{
//      TODO: 25.02.2020
//    }
}