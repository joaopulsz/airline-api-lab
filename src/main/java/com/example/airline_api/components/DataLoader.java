package com.example.airline_api.components;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import com.example.airline_api.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    FlightService flightService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Passenger passenger1 = new Passenger("Joao", "joao@gmail.com");
        Passenger passenger2 = new Passenger("Maria", "maria@gmail.com");
        Passenger passenger3 = new Passenger("Elisa", "elisa@outlook.com");
        Passenger passenger4 = new Passenger("Rob", "rob@yahoo.com");
        Passenger passenger5 = new Passenger("Edward", "ed@outlook.com");
        Passenger passenger6 = new Passenger("Matilda", "mat@yahoo.com");

        passengerRepository.save(passenger1);
        passengerRepository.save(passenger2);
        passengerRepository.save(passenger3);
        passengerRepository.save(passenger4);
        passengerRepository.save(passenger5);
        passengerRepository.save(passenger6);

        Flight flight1 = new Flight("Rio de Janeiro", 100, "10 March 2023", new Time(22, 00, 00));
        Flight flight2 = new Flight("Munich", 100, "10 March 2023", new Time(22, 00, 00));
        Flight flight3 = new Flight("Melbourne", 100, "10 March 2023", new Time(22, 00, 00));
        Flight flight4 = new Flight("Cape Town", 80, "10 March 2023", new Time(22, 00, 00));
        Flight flight5 = new Flight("Quebec", 80, "10 March 2023", new Time(22, 00, 00));

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);
        flightRepository.save(flight4);
        flightRepository.save(flight5);

        flightService.bookPassengerOntoFlight(flight1, passenger1);
        flightService.bookPassengerOntoFlight(flight1, passenger2);
        flightService.bookPassengerOntoFlight(flight3, passenger3);
        flightService.bookPassengerOntoFlight(flight3, passenger4);
        flightService.bookPassengerOntoFlight(flight5, passenger6);
    }
}
