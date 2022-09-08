package com.example.airline_api.controllers;

import com.example.airline_api.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/flights")
public class FlightController {

    @Autowired
    FlightService flightService;


}
