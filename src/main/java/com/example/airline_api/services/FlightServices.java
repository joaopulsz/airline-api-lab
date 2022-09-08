package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServices {

    @Autowired
    FlightRepository flightRepository;

    public void addFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(int id) {
        return flightRepository.findById(id);
    }

    public void bookPassengerOntoFlight(Flight flight, Passenger passenger) {
        flight.getPassengers().add(passenger);
    }

    public void cancelFlight(Flight flight) {
        flightRepository.delete(flight);
    }

}
