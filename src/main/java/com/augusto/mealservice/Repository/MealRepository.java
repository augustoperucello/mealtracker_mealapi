package com.augusto.mealservice.Repository;

import com.augusto.mealservice.Entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    public Optional<Meal> findByName(String name);
    @Query(value = "SELECT * FROM meals WHERE user_id = ?1 AND meal_date = ?2", nativeQuery = true)
    public List<Meal> findByUserId(long userId, Date date);
}
