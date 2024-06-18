package org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class ReservationItemResponse {

    private UUID itemId;
    private ReservationType type;
    private String category;
    private String city;
    private LocalDateTime dateTime;
    private Integer price;
}