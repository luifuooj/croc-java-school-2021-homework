package ru.luifuooj.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Структура данных о входящих рейсах.
 */
public class IncomingFlightData {

    @XmlElement(name = "Flight")
    private String flightNumber;

    @XmlElement(name = "Airline")
    private String airline;

    @XmlElement(name = "From")
    private String pointOfDeparture;

    @XmlTransient
    private LocalDateTime departureDateTime;

    @XmlElement(name = "Departure")
    private String departureDateTimeString;

    @XmlElement(name = "Duration")
    private String travelTimeString;

    @XmlTransient
    private LocalTime travelTime;

    @XmlTransient
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @XmlTransient
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public IncomingFlightData() {
    }

    public IncomingFlightData(String flightNumber, String airline, String pointOfDeparture,
                              LocalDateTime departureDateTime, LocalTime travelTime) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.pointOfDeparture = pointOfDeparture;
        this.departureDateTime = departureDateTime;
        this.travelTime = travelTime;
        this.departureDateTimeString = dateTimeFormatter.format(departureDateTime);
        this.travelTimeString = timeFormatter.format(travelTime);
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
        this.departureDateTimeString = dateTimeFormatter.format(departureDateTime);
        this.departureDateTime = departureDateTime;
    }

    public String getDepartureDateTimeString() {
        return departureDateTimeString;
    }

    public void setDepartureDateTimeString(String departureDateTimeString) {
        this.departureDateTime = LocalDateTime.parse(departureDateTimeString, dateTimeFormatter);
        this.departureDateTimeString = departureDateTimeString;
    }

    public String getTravelTimeString() {
        return travelTimeString;
    }

    public void setTravelTimeString(String travelTimeString) {
        this.travelTime = LocalTime.parse(travelTimeString, timeFormatter);
        this.travelTimeString = travelTimeString;
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalTime travelTime) {
        this.travelTimeString = timeFormatter.format(travelTime);
        this.travelTime = travelTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomingFlightData that = (IncomingFlightData) o;
        return flightNumber.equals(that.flightNumber) &&
                airline.equals(that.airline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, airline);
    }

    @Override
    public String toString() {
        return "IncomingFlightData{" +
                "flightNumber='" + flightNumber + '\'' +
                ", airline='" + airline + '\'' +
                ", pointOfDeparture='" + pointOfDeparture + '\'' +
                ", departureDateTime=" + departureDateTime +
                ", departureDateTimeString='" + departureDateTimeString + '\'' +
                ", travelTimeString='" + travelTimeString + '\'' +
                ", travelTime=" + travelTime +
                ", dateTimeFormatter=" + dateTimeFormatter +
                ", timeFormatter=" + timeFormatter +
                '}';
    }
}
