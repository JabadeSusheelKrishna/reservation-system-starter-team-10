package flight.reservation;

import flight.reservation.flight.ScheduledFlight;
import flight.reservation.order.FlightOrder;
import flight.reservation.order.Order;
import flight.reservation.order.FlightOrderFactory;
import flight.reservation.order.OrderFactory;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String email;
    private String name;
    private List<Order> orders;
    private final OrderFactory orderFactory;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.orders = new ArrayList<>();
        this.orderFactory = new FlightOrderFactory(); // Inject factory instance
    }

    public FlightOrder createOrder(List<String> passengerNames, List<ScheduledFlight> flights, double price) {
        return (FlightOrder) orderFactory.createOrder(this, passengerNames, flights, price);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
