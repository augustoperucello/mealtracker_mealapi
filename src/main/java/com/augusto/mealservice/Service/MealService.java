package com.augusto.mealservice.Service;

import com.augusto.mealservice.Entities.Meal;
import com.augusto.mealservice.Exceptions.EmptyListException;
import com.augusto.mealservice.Exceptions.ResourceNotFoundException;
import com.augusto.mealservice.Repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;
    public ResponseEntity<Meal> updateMealById(Long mealId, Meal mealDetails) throws ResourceNotFoundException {
        Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new ResourceNotFoundException("Meal with ID :: "+ mealId + " not found"));
        meal.setName(mealDetails.getName());
        meal.setCategory(mealDetails.getCategory());
        meal.setCalories(mealDetails.getCalories());
        meal.setUserId(mealDetails.getUserId());
        meal.setUpdatedAt(new Date());
        meal.setMealDate(mealDetails.getMealDate());
        final Meal updatedMeal = mealRepository.save(meal);
        return ResponseEntity.ok(updatedMeal);
    }

    public List<Meal> getAllMeals() throws ResourceNotFoundException{
        return mealRepository.findAll();
    }

    public Meal getMealById(Long Id) throws ResourceNotFoundException{
        Meal meal = mealRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException ("Meal with ID :: " + Id + " not found"));
        return meal;
    }

    //In this method, we are searching a Meal by a specific user in a specific day
    public List<Meal> getAllMealsByUserId(Long userId, Date date) throws EmptyListException {
        List<Meal> meal = mealRepository.findByUserId(userId, date);
        if(CollectionUtils.isEmpty(meal))
        {
            throw new EmptyListException("User :: " + userId  + " has no meals registered at " + date);
        }
        return meal;
    }

    public Meal getMealByName(String name) throws ResourceNotFoundException{
        Meal meal = mealRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException ("Meal named:: " + name + "  not found"));
        return meal;
    }

    public Meal createNewMeal(Meal meal){
        return mealRepository.save(meal);
    }

    public Map<String, Boolean> deleteMeal(Long Id)throws ResourceNotFoundException{
        Meal meal = mealRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Meal with ID :: " + Id + " not found"));
        mealRepository.delete(meal);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

