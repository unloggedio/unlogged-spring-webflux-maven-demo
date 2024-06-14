package org.unlogged.springwebfluxdemo.resilientPatterns.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class RestaurantAggregate {

    private Integer id;
    private String cuisine;
    private String description;
    private List<Review> reviews;
}
