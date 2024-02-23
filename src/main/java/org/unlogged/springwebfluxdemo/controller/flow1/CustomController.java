package org.unlogged.springwebfluxdemo.controller.flow1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.service.flow1.CustomService;

@RestController
@RequestMapping("/custom")
public class CustomController extends CustomControllerCE {

    @Autowired
    public CustomController(CustomService customService) {
        super(customService);
    }
}