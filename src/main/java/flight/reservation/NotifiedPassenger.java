package flight.reservation;

import flight.reservation.Passenger;
import flight.reservation.FlightObserver;

public class NotifiedPassenger extends Passenger implements FlightObserver {

    public NotifiedPassenger(String name) {
        super(name);
    }

    @Override
    public void update(String flightNumber, String status) {
        System.out.println("Notification for " + getName() + ": " + flightNumber + " is now " + status);
    }
}
