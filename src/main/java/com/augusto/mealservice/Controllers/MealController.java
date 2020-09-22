package com.augusto.mealservice.Controllers;

import com.augusto.mealservice.Exceptions.EmptyListException;
import com.augusto.mealservice.Exceptions.ResourceNotFoundException;
import com.augusto.mealservice.Entities.Meal;
import com.augusto.mealservice.Repository.MealRepository;
import com.augusto.mealservice.Service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MealController {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private MealService mealService;

    @GetMapping("/meals")
    public List<Meal> getAllMeals()throws ResourceNotFoundException{
        return mealService.getAllMeals();
    }

    @GetMapping("/user/{id}/meal")
    //ResponseEntity<Meal>
    public ResponseEntity<List<Meal>> getAllMealsByUser(@PathVariable(value="id") long id, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)throws EmptyListException {
        List<Meal> meal =  mealService.getAllMealsByUserId(id,date);
        return ResponseEntity.ok().body(meal);
    }

    @GetMapping("/meals/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable(value="id") Long id)
        throws ResourceNotFoundException {
        return ResponseEntity.ok().body(mealService.getMealById(id));
    }

    @PostMapping("/meals")
    public Meal createMeal(@Valid @RequestBody Meal meal){
        return mealService.createNewMeal(meal);
    }

    @PutMapping("/meals/{id}")
    public ResponseEntity<Meal> updateMealById(
        @PathVariable(value="id") Long mealId, @Valid @RequestBody Meal mealDetails)
        throws ResourceNotFoundException{
            return mealService.updateMealById(mealId, mealDetails);
        }

    @DeleteMapping("/meals/{id}")
    public Map<String, Boolean> deleteMeal (@PathVariable(value="id") Long mealId) throws Exception {
        return mealService.deleteMeal(mealId);
    }

    }

