package org.unlogged.springwebfluxdemo.nestedPojo.service;

import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.nestedPojo.AddressDto;
import org.unlogged.springwebfluxdemo.nestedPojo.ContactDto;
import org.unlogged.springwebfluxdemo.nestedPojo.GeoLocationDto;
import org.unlogged.springwebfluxdemo.nestedPojo.TeacherDto;

import java.util.Arrays;
import java.util.List;

@Service
public class ContentEnrichmentService {

    public TeacherDto enrichTeacherDetails(String teacherId) {
        // Mocked data for demonstration
        GeoLocationDto geoLocation = new GeoLocationDto(37.7749, -122.4194);
        ContactDto contact = new ContactDto("teacher@example.com", "123-456-7890", geoLocation);
        ContactDto contact2 = new ContactDto("teacherother@example.com", "223-456-7890", geoLocation);
        AddressDto address = new AddressDto("123 Main St", "San Francisco", Arrays.asList(contact, contact2));
        return new TeacherDto(teacherId, "John Doe", address);
    }

    public List<TeacherDto> getAllTeachers() {
        // Mocked data for demonstration
        return Arrays.asList(
                enrichTeacherDetails("1"),
                enrichTeacherDetails("2"),
                enrichTeacherDetails("3")
        );
    }

}
