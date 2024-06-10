package org.unlogged.springwebfluxdemo.model;

import lombok.Data;

@Data
public class Player {
    private Long id;
    private String name;
    private int age;
    private String team;
}