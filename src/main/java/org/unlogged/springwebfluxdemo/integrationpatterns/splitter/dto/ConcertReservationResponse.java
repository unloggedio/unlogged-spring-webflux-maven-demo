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
public class ConcertReservationResponse {

    private UUID reservationId;
    private String city;
    private LocalDateTime slot;
    private String category;
    private Integer price;

}