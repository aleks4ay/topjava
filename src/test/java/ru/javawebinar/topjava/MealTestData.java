package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;
    public static final int MEAL_ID_2 = MEAL_ID + 1;
    public static final int MEAL_ID_3 = MEAL_ID + 2;
    public static final int USER_ID = START_SEQ;

    public static final Meal MEAL = new
            Meal(MEAL_ID, LocalDateTime.of(2020,Month.FEBRUARY, 15,10,00), "Завтрак", 500);
    public static final Meal MEAL_2 = new
            Meal(MEAL_ID_2, LocalDateTime.of(2020,Month.FEBRUARY, 15,13,00), "Обед", 850);
    public static final Meal MEAL_3 = new
            Meal(MEAL_ID_3, LocalDateTime.of(2020,Month.FEBRUARY, 15,20,00), "Ужин", 750);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "newMeal", 555);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL);
        updated.setDescription("UpdatedMeal");
        updated.setCalories(333);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
