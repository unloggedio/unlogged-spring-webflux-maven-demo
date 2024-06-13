package org.unlogged.springwebfluxdemo.resilientPatterns.externalservices.review;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.resilientPatterns.dto.Review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/resilient")
public class MockReviewController {

    @GetMapping("/review/{id}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Integer id) throws InterruptedException {
        // Simulate fetching reviews based on restaurant ID
        Review review1;
        Review review2;

        if(id ==1) {
            review1 = new Review(1, "User1", 4, "Great food");
            review2 = new Review(1, "User2", 5, "Excellent service");
        }
        else if (id == 2) {
            // Simulate fetching reviews for restaurant with id == 2 and 600 ms delay to mimic timeout
            review1 = new Review(2, "User3", 3, "Average experience");
            review2 = new Review(2, "User4", 4, "Good ambiance");

            // Introduce a delay of 600 milliseconds (0.6 seconds) for id == 2
            Thread.sleep(600);
        } else if(id == 3) {
            // For id == 3, throw a server error
//            throw new InterruptedException("Simulated server error");
            System.out.println("Retried this call");
            return ResponseEntity.internalServerError().build();
        } else {
            System.out.println("Should not print multiple times, as retries should not happen for not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Arrays.asList(review1, review2));
    }
}
