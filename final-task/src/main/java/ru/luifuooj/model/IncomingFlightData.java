package ru.luifuooj.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Структура данных о входящих рейсах.
 */
public class IncomingFlightData {
    @XmlElement(name = "Flight number")
    private String flightNumber;
    @XmlElement(name = "Airline")
    private String airline;
    @XmlElement(name = "Point of departure")
    private String pointOfDeparture;
    @XmlTransient
    private LocalDateTime departureDateTime;
    @XmlElement(name = "Departure date and time")
    private String departureDateTimeString;
    @XmlElement(name = "Travel time")
    private String travelTimeString;
    @XmlTransient
    private LocalTime travelTime;
    @XmlTransient
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    @XmlTransient
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public IncomingFlightData(String flightNumber, String airline, String pointOfDeparture, LocalDateTime departureDateTime, LocalTime travelTime) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.pointOfDeparture = pointOfDeparture;
        this.departureDateTimeString = convertDateToString(departureDateTime);
        this.travelTimeString = convertTimeToString(travelTime);
    }

    private String convertDateToString(LocalDateTime date) {
        return date.format(dateFormatter);
    }

    private LocalDateTime parseDateFromString(String date) {
        return LocalDateTime.parse(date, dateFormatter);
    }

    private String convertTimeToString(LocalTime time) {
        return time.format(timeFormatter);
    }

    private LocalTime parseTimeFromString(String time) {
        return LocalTime.parse(time, timeFormatter);
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getPointOfDeparture() {
        return pointOfDeparture;
    }

    public void setPointOfDeparture(String pointOfDeparture) {
        this.pointOfDeparture = pointOfDeparture;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
        this.departureDateTimeString = convertDateToString(departureDateTime);
    }

    public String getDepartureDateTimeString() {
        return departureDateTimeString;
    }

    public void setDepartureDateTimeString(String departureDateTimeString) {
        this.departureDateTimeString = departureDateTimeString;
        this.departureDateTime = parseDateFromString(departureDateTimeString);
    }

    public String getTravelTimeString() {
        return travelTimeString;
    }

    public void setTravelTimeString(String travelTimeString) {
        this.travelTimeString = travelTimeString;
        this.travelTime = parseTimeFromString(travelTimeString);
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalTime travelTime) {
        this.travelTime = travelTime;
        this.travelTimeString = convertTimeToString(travelTime);
    }

}
