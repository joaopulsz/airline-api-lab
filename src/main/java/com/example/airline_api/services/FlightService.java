package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public Flight addFlight(Flight flight) {
        flightRepository.save(flight);
        return flight;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(int id) {
        return flightRepository.findById(id);
    }

    public Optional<Flight> bookPassengerOntoFlight(Flight flight, Passenger passenger) {
        if (flight.getCapacity() == flight.getPassengers().size()) {
            return null;
        } else {
            flight.getPassengers().add(passenger);
            passenger.getFlights().add(flight);
            passengerRepository.save(passenger);
            return Optional.of(flight);
        }
    }

    public void cancelFlight(Flight flight) {
        flightRepository.delete(flight);
    }

}
