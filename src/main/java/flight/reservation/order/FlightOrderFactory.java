package flight.reservation.order;

import flight.reservation.Customer;
import flight.reservation.Passenger;
import flight.reservation.flight.ScheduledFlight;

import java.util.ArrayList;
import java.util.List;

public class FlightOrderFactory implements OrderFactory {

    @Override
    public FlightOrder createOrder(Customer customer, List<String> passengerNames, List<ScheduledFlight> flights, double price) {
        if (!isOrderValid(customer, passengerNames, flights)) {
            throw new IllegalStateException("Order is not valid");
        }

        FlightOrder order = new FlightOrder(flights);
        order.setCustomer(customer);
        order.setPrice(price);

        List<Passenger> passengers = new ArrayList<>();
        for (String name : passengerNames) {
            passengers.add(new Passenger(name));
        }

        order.setPassengers(passengers);

        for (ScheduledFlight scheduledFlight : order.getScheduledFlights()) {
            scheduledFlight.addPassengers(passengers);
        }

        customer.getOrders().add(order);
        return order;
    }

    private boolean isOrderValid(Customer customer, List<String> passengerNames, List<ScheduledFlight> flights) {
        boolean valid = true;
        valid = valid && !FlightOrder.getNoFlyList().contains(customer.getName());
        valid = valid && passengerNames.stream().noneMatch(passenger -> FlightOrder.getNoFlyList().contains(passenger));
        valid = valid && flights.stream().allMatch(scheduledFlight -> {
            try {
                return scheduledFlight.getAvailableCapacity() >= passengerNames.size();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return false;
            }
        });
        return valid;
    }
}
