package com.oauth2learning.loginapp;

import com.augusto.mealservice.Entities.Meal;
import com.augusto.mealservice.LoginappApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoginappApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginappApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl(){
		return "http//localhost:" + port;
	}

	//Simple test, checks if context loads
	@Test
	void contextLoads() {
	}

	//First testing getting all users
	@Test
	public void testGetAllMeals(){
		HttpHeaders headers = new HttpHeaders();
		//This simply joins body and header
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/meals", HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testCreateUser() {
		Meal meal = new Meal();
		meal.setName("Tapioca");
		meal.setCategory("Carboidratos");
		meal.setCalories(390);
		meal.setUserId(1);
		meal.setUpdatedAt(new Date());

		ResponseEntity<Meal> postResponse = restTemplate.postForEntity(getRootUrl() + "/meals", meal, Meal.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatePost(){
		int id = 1;
		Meal meal = restTemplate.getForObject(getRootUrl() + "/meals" + id, Meal.class);
		meal.setName("Crepioca");

		restTemplate.put(getRootUrl() + "/meals/" + id, meal);

		Meal updatedMeal = restTemplate.getForObject(getRootUrl() + "/meals/" + id, Meal.class);
		Assert.assertNotNull(updatedMeal);
	}

	@Test
	public void testDeletePost(){
		int id = 2;
		Meal meal = restTemplate.getForObject(getRootUrl() + "/meals/" + id, Meal.class);
		Assert.assertNotNull(meal);
		restTemplate.delete(getRootUrl() + "/meals/" + id);

		try {
			meal = restTemplate.getForObject(getRootUrl() + "/meals/" +id, Meal.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}


}
