package ru.luifuooj.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Структура данных об исходящих рейсах.
 */
public class OutboundFlightData {

    @XmlElement(name = "Flight")
    private String flightNumber;

    @XmlElement(name = "Airline")
    private String airline;

    @XmlElement(name = "To")
    private String pointOfArrival;

    @XmlElement(name = "Arrival")
    private String arrivalDateTimeString;

    @XmlTransient
    private LocalDateTime arrivalDateTime;

    @XmlElement(name = "Duration")
    private String travelTimeString;

    @XmlTransient
    private LocalTime travelTime;

    @XmlTransient
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @XmlTransient
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public OutboundFlightData(String flightNumber, String airline, String pointOfArrival,
                              LocalDateTime arrivalDateTime, LocalTime travelTime) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.pointOfArrival = pointOfArrival;
        this.arrivalDateTime = arrivalDateTime;
        this.travelTime = travelTime;
        this.arrivalDateTimeString = dateTimeFormatter.format(arrivalDateTime);
        this.travelTimeString = timeFormatter.format(travelTime);
    }

    public OutboundFlightData() {
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
        this.arrivalDateTime = LocalDateTime.parse(arrivalDateTimeString, dateTimeFormatter);
        this.arrivalDateTimeString = arrivalDateTimeString;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTimeString = dateTimeFormatter.format(arrivalDateTime);
        this.arrivalDateTime = arrivalDateTime;
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
        OutboundFlightData that = (OutboundFlightData) o;
        return Objects.equals(flightNumber, that.flightNumber) &&
                Objects.equals(airline, that.airline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, airline);
    }

    @Override
    public String toString() {
        return "OutboundFlightData{" +
                "flightNumber='" + flightNumber + '\'' +
                ", airline='" + airline + '\'' +
                ", pointOfArrival='" + pointOfArrival + '\'' +
                ", arrivalDateTimeString='" + arrivalDateTimeString + '\'' +
                ", arrivalDateTime=" + arrivalDateTime +
                ", travelTimeString='" + travelTimeString + '\'' +
                ", travelTime=" + travelTime +
                ", dateTimeFormatter=" + dateTimeFormatter +
                ", timeFormatter=" + timeFormatter +
                '}';
    }
}
