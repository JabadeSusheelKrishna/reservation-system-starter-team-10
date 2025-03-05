package flight.reservation.flight;

import flight.reservation.flight.FlightSubject;
import flight.reservation.FlightObserver;
import flight.reservation.flight.Flight;
import flight.reservation.Airport;
import java.util.ArrayList;
import java.util.List;

public class ObservableFlight extends Flight implements FlightSubject {
    private String status; // Flight status (e.g., On Time, Delayed, Cancelled)
    private List<FlightObserver> FlightObservers; 

    public ObservableFlight(int number, Airport departure, Airport arrival, Object aircraft) throws IllegalArgumentException {
        super(number, departure, arrival, aircraft); // Call the existing Flight constructor
        this.status = "On Time"; // Default status
        this.FlightObservers = new ArrayList<>();
    }

    public void cancelFlight() {
        this.status = "Cancelled";
        notifyObservers();
    }

    public void delayFlight(String reason) {
        this.status = "Delayed: " + reason;
        notifyObservers();
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers(); 
    }

    public String getStatus() {
        return status;
    }

    @Override
    public void addObserver(FlightObserver observer) {
        FlightObservers.add(observer);
    }

    @Override
    public void removeObserver(FlightObserver observer) {
        FlightObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (FlightObserver observer : FlightObservers) {
            observer.update("Flight " + getNumber(), status);
        }
    }
}
