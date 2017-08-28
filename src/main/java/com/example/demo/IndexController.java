package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by avakil on 8/23/17.
 * This class routs the page to template invoice.html (View from MVC)
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/invoice")
    public String index(){ return "invoice";}



}
