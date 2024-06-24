package org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ReservationItemRequest {

    private ReservationType type;
    private String category;
    private String city;
    private LocalDateTime dateTime;

}
