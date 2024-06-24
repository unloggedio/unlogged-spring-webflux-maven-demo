package org.unlogged.springwebfluxdemo.resilientPatterns.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    private Integer id;
    private String user;
    private Integer rating;
    private String comment;

}