package org.unlogged.springwebfluxdemo.resilientPatterns.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Restaurant {
    private Integer id;
    private String cuisine;
    private String description;
    private Integer price;

}
