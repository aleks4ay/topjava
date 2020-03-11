package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Query("SELECT m FROM Meal m WHERE m.user.id=?1 ORDER BY m.dateTime DESC")
    List<Meal> getAllByUserId( int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=?3 and m.dateTime>=?1 and m.dateTime<?2 ORDER BY m.dateTime DESC")
    List<Meal> findAllBetweenByUserId(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}
