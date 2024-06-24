package org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class CarrierResponse {

    private String serviceProvider;
    private String from;
    private String to;
    private Double price;
    private LocalDate date;
}