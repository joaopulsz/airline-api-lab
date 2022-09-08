package com.example.airline_api.controllers;

import com.example.airline_api.models.Passenger;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers(@RequestParam Optional<Boolean> hasBooking) {
        List<Passenger> passengers;
        if (hasBooking.isPresent()) {
            passengers = passengerService.getAllBookedPassengers();
        } else {
            passengers = passengerService.getAllNotBookedPassengers();
        }
        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable int id) {
        Optional<Passenger> passenger = passengerService.getPassengerById(id);
        if (passenger.isPresent()){
            return new ResponseEntity<>(passenger.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Passenger> createNewPassenger(@RequestBody Passenger passenger) {
        Passenger newPassenger = passengerService.addPassenger(passenger);
        return new ResponseEntity<>(newPassenger, HttpStatus.CREATED);
    }
}
