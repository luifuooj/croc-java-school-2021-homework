package ru.luifuooj.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Структура данных об исходящих рейсах.
 */
public class OutboundFlightData {
    @XmlElement(name = "Flight number")
    private String flightNumber;
    @XmlElement(name = "Airline")
    private String airline;
    @XmlElement(name = "Point of arrival")
    private String pointOfArrival;
    @XmlElement(name = "Arrival date and time")
    private String arrivalDateTimeString;
    @XmlTransient
    private LocalDateTime arrivalDateTime;
    @XmlElement(name = "Travel time")
    private String travelTimeString;
    @XmlTransient
    private LocalTime travelTime;
    @XmlTransient
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    @XmlTransient
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public OutboundFlightData(String flightNumber, String airline, String pointOfArrival,
                              LocalDateTime arrivalDateTime, LocalTime travelTime) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.pointOfArrival = pointOfArrival;
        this.arrivalDateTimeString = convertDateToString(arrivalDateTime);
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

    public String getPointOfArrival() {
        return pointOfArrival;
    }

    public void setPointOfArrival(String pointOfArrival) {
        this.pointOfArrival = pointOfArrival;
    }

    public String getArrivalDateTimeString() {
        return arrivalDateTimeString;
    }

    public void setArrivalDateTimeString(String arrivalDateTimeString) {
        this.arrivalDateTimeString = arrivalDateTimeString;
        this.arrivalDateTime = parseDateFromString(arrivalDateTimeString);
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
        this.arrivalDateTimeString = convertDateToString(arrivalDateTime);
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalTime travelTime) {
        this.travelTime = travelTime;
        this.travelTimeString = convertTimeToString(travelTime);
    }
}
