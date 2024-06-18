package org.unlogged.springwebfluxdemo.nestedPojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactDto {
    private String email;
    private String phone;
    private GeoLocationDto locations;
}

