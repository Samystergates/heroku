package com.rentmen.app.configurations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class SpaController {
    @GetMapping("/{path:[^\\.]*}")
    public String forward() {
        return "forward:/";
    }
}