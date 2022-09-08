package com.example.airline_api.services;

import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    public Passenger addPassenger(Passenger passenger) {
        passengerRepository.save(passenger);
        return passenger;
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Optional<Passenger> getPassengerById(int id) {
        return passengerRepository.findById(id);
    }

    public List<Passenger> getAllBookedPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        this.getAllPassengers().forEach(passenger -> {
            if (passenger.getFlights().size() > 0) {
                passengers.add(passenger);
            }
        });
        return passengers;
    }

    public List<Passenger> getAllNotBookedPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        this.getAllPassengers().forEach(passenger -> {
            if (passenger.getFlights().size() == 0) {
                passengers.add(passenger);
            }
        });
        return passengers;
    }
}
