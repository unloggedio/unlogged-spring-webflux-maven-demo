package org.unlogged.springwebfluxdemo.nestedPojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDto {
    private String street;
    private String city;
    private List<ContactDto> contacts;
}