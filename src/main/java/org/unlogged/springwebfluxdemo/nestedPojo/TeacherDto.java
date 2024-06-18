package org.unlogged.springwebfluxdemo.nestedPojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherDto {
    private String id;
    private String name;
    private AddressDto address;
}