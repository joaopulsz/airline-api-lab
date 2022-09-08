package com.example.airline_api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column(name = "email_address")
    private String emailAddress;

    @ManyToMany
    @JoinTable(
            name = "passengers_flights",
            joinColumns = {@JoinColumn(name = "passenger_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "flight_id", nullable = false)}
    )
    @JsonIgnoreProperties({"passengers"})
    private List<Flight> flights;

    public Passenger(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.flights = new ArrayList<>();
    }

    public Passenger() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}