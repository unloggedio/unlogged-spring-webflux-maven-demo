package org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.externalservices.bluedart;

import lombok.Data;
import lombok.ToString;
import java.time.LocalDate;

@Data
@ToString
public class BlueDartResponse {

    private String serviceProvider;
    private String from;
    private String to;
    private Double price;
    private LocalDate date;
}