package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.services.FlightService;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable int id) {
        Optional<Flight> flight = flightService.getFlightById(id);
        return flight.isPresent() ? new ResponseEntity<>(flight.get(), HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Flight> createNewFlight(@RequestBody Flight flight) {
        Flight newFlight = flightService.addFlight(flight);
        return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Flight> bookPassengerOntoFlightById(@PathVariable int flightId, @RequestBody int passengerId) {
        Optional<Flight> flight = flightService.getFlightById(flightId);
        Optional<Passenger> passenger = passengerService.getPassengerById(passengerId);

        if (flight.isEmpty() || passenger.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            flightService.bookPassengerOntoFlight(flight.get(), passenger.get());
            return new ResponseEntity<>(flight.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<Flight> cancelFlightById(@PathVariable int id) {
        Flight flightToCancel = flightService.getFlightById(id).get();
        flightService.cancelFlight(flightToCancel);
        return new ResponseEntity<>(flightToCancel, HttpStatus.OK);
    }


}
