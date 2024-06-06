package org.unlogged.springwebfluxdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.service.CronService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cron")
public class CronController {

    private final CronService cronService;

    @Autowired
    public CronController(CronService cronService) {
        this.cronService = cronService;
    }

    @GetMapping("/hello")
    public Mono<String> cronHello() {
        return cronService.getCount().map(count ->"Hello Cron: " + count);
    }
}
