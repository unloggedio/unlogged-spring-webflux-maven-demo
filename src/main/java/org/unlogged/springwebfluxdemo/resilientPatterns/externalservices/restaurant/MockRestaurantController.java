package org.unlogged.springwebfluxdemo.resilientPatterns.externalservices.restaurant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.resilientPatterns.dto.Restaurant;

@RestController
@RequestMapping("/resilient")
public class MockRestaurantController {

    @GetMapping("/restaurant/{id}")
    public Restaurant getRestaurant(@PathVariable Integer id) {
        // Simulate fetching restaurant data based on ID
        Restaurant restaurant = new Restaurant();
        if(id == 1) {
            restaurant.setId(id);
            restaurant.setCuisine("Italian");
            restaurant.setDescription("Delicious Italian cuisine");
            restaurant.setPrice(50);
        }
        if(id == 2) {
            restaurant.setId(id);
            restaurant.setCuisine("French");
            restaurant.setDescription("Exquisite French cuisine");
            restaurant.setPrice(80);
        }
        return restaurant;
    }
}

