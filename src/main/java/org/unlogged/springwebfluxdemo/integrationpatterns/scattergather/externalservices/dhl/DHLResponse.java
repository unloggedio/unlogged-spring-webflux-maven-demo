package org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.externalservices.dhl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
public class DHLResponse {
    private Double price;
    private LocalDate date;
}
