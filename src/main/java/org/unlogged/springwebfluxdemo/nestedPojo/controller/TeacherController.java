package org.unlogged.springwebfluxdemo.nestedPojo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.nestedPojo.TeacherDto;
import org.unlogged.springwebfluxdemo.nestedPojo.service.ContentEnrichmentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@RestController
public class TeacherController {

    private final ContentEnrichmentService contentEnrichmentService;

    @Autowired
    public TeacherController(ContentEnrichmentService contentEnrichmentService) {
        this.contentEnrichmentService = contentEnrichmentService;
    }

    @GetMapping("/teachers/{teacherId}")
    public Mono<ResponseEntity<TeacherDto>> getTeacherById(@PathVariable String teacherId) {
        TeacherDto teacherDto = contentEnrichmentService.enrichTeacherDetails(teacherId);
        return Mono.just(ResponseEntity.ok(teacherDto));
    }

    @GetMapping
    public ResponseEntity<Flux<TeacherDto>> getAllTeachers() {
        List<TeacherDto> teachers = contentEnrichmentService.getAllTeachers();
        Flux<TeacherDto> teacherFlux = Flux.fromIterable(teachers);
        return ResponseEntity.ok(teacherFlux);
    }
}

