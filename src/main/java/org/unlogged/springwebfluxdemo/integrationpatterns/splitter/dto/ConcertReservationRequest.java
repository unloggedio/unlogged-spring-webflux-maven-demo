package org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class ConcertReservationRequest {

    private String city;
    private LocalDateTime slot;
    private String category;
}
