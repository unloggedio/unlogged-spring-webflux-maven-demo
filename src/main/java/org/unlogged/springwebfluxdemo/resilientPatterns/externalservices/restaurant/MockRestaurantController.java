package org.unlogged.springwebfluxdemo.resilientPatterns.externalservices.restaurant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.resilientPatterns.dto.Restaurant;

@RestController
@RequestMapping("/resilient")
public class MockRestaurantController {

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Integer id) {
        // Simulate fetching restaurant data based on ID
        Restaurant restaurant = new Restaurant();
        if(id == 1) {
            restaurant.setId(id);
            restaurant.setCuisine("Italian");
            restaurant.setDescription("Delicious Italian cuisine");
            restaurant.setPrice(50);
        } else if(id == 2) {
            restaurant.setId(id);
            restaurant.setCuisine("French");
            restaurant.setDescription("Exquisite French cuisine");
            restaurant.setPrice(80);
        } else if(id == 3) {
            restaurant.setId(id);
            restaurant.setCuisine("Japanese");
            restaurant.setDescription("Japanese good cuisine");
            restaurant.setPrice(100);
        }
        else if(id ==4) {
            restaurant.setId(id);
            restaurant.setCuisine("Indian");
            restaurant.setDescription("Mimicking an indian Item for which no reviews yet");
            restaurant.setPrice(90);
        } else if(id == 5) {
            return ResponseEntity.internalServerError().build();
        } else if(id ==6) {
            System.out.println("Should not print multiple times, as retries should not happen for not found");
            return ResponseEntity.notFound().build();
        } else {
            restaurant.setId(id);
            restaurant.setCuisine("Mexican");
            restaurant.setDescription("Any other food id is mexican");
            restaurant.setPrice(90);
        }
        return ResponseEntity.ok(restaurant);
    }
}


