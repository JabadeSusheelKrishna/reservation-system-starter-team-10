package flight.reservation.flight;

import java.util.Arrays;

import flight.reservation.Airport;
import flight.reservation.plane.vehicle;

public class Flight {

    private int number;
    private Airport departure;
    private Airport arrival;
    protected Object aircraft;

    public Flight(int number, Airport departure, Airport arrival, Object aircraft) throws IllegalArgumentException {
        this.number = number;
        this.departure = departure;
        this.arrival = arrival;
        this.aircraft = aircraft;
        checkValidity();
    }

    private void checkValidity() throws IllegalArgumentException {
        if (!isAircraftValid(departure) || !isAircraftValid(arrival)) {
            throw new IllegalArgumentException("Selected aircraft is not valid for the selected route.");
        }
    }

    private boolean isAircraftValid(Airport airport) {
        // checks if a given aircraft is allowed to operate at a specific airport based on its model.
        if (!(this.aircraft instanceof vehicle)) {
            throw new IllegalArgumentException("Aircraft is not recognized");
        }
        String model = ((vehicle) this.aircraft).getModel();
        return Arrays.stream(airport.getAllowedAircrafts()).anyMatch(x -> x.equals(model));
    }

    public Object getAircraft() {
        return aircraft;
    }

    public int getNumber() {
        return number;
    }

    public Airport getDeparture() {
        return departure;
    }

    public Airport getArrival() {
        return arrival;
    }

    @Override
    public String toString() {
        return aircraft.toString() + "-" + number + "-" + departure.getCode() + "/" + arrival.getCode();
    }

}
