# Software Engineering - Class Activity - 6
## Team - 10: Event-Driven Engineers

---

## Strategy Design Pattern

### Modified Files:
- `flight.reservation.plane.*.java`
- `flight.reservation.flight.Flight.java`
- `flight.reservation.flight.ScheduleFlight.java`

### Why Did We Use the Strategy Design Pattern?
- There are different types of flights with varying methods and attributes. Maintaining all of them in a single class is difficult, and extending functionality in the future becomes cumbersome.
- To overcome these issues, we use the **Strategy Design Pattern**, which helps encapsulate different behaviors and allows easy extensibility.

### How Did We Use the Strategy Design Pattern?
- We first created an interface called `Vehicle` containing a few functions.
- This enforces all new aircraft classes to implement certain mandatory functionalities.
- We then modified the existing classes: `Helicopter`, `PassengerDrone`, and `PassengerPlane`, ensuring they conform to the `Vehicle` interface.
- We utilized **polymorphism**, allowing the same interface to be implemented differently in each subclass.

### Code Changes Made:
```java
// Vehicle interface
public interface Vehicle {
    public String getModel();
    public int getCrewCapacity();
    public int getPassengerCapacity();
}

// Helicopter class
public class Helicopter implements Vehicle {
    @Override
    public String getModel() {
        return model;
    }
    
    @Override
    public int getCrewCapacity() {
        return crewCapacity;
    }
    
    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }
}

// PassengerDrone class
public class PassengerDrone implements Vehicle {
    public PassengerDrone(String model) {
        this.model = model;
    }
    
    @Override
    public String getModel() {
        return model;
    }
    
    @Override
    public int getCrewCapacity() {
        return crewCapacity;
    }
    
    @Override
    public int getPassengerCapacity() {
        return 4;
    }
}

// PassengerPlane class
public class PassengerPlane implements Vehicle {
    public PassengerPlane(String model) {
        this.model = model;
    }
    
    @Override
    public String getModel() {
        return this.model;
    }
    
    @Override
    public int getCrewCapacity() {
        return this.crewCapacity;
    }
    
    @Override
    public int getPassengerCapacity() {
        return this.passengerCapacity;
    }
}

// Changes made in Flight.java
private boolean isAircraftValid(Airport airport) {
    if (!(this.aircraft instanceof Vehicle)) {
        throw new IllegalArgumentException("Aircraft is not recognized");
    }
    String model = ((Vehicle) this.aircraft).getModel();
    return Arrays.stream(airport.getAllowedAircrafts()).anyMatch(x -> x.equals(model));
}

// Changes made in ScheduleFlight.java
public int getCrewMemberCapacity() throws NoSuchFieldException {
    if (this.aircraft instanceof Vehicle) {
        return ((Vehicle) this.aircraft).getCrewCapacity();
    }
    throw new NoSuchFieldException("This aircraft has no information about its crew capacity");
}

public int getCapacity() throws NoSuchFieldException {
    if (this.aircraft instanceof Vehicle) {
        return ((Vehicle) this.aircraft).getPassengerCapacity();
    }
    throw new NoSuchFieldException("This aircraft has no information about its capacity");
}
```

---

## Adapter Design Pattern

### Modified Files:
- `flight.reservation.payment.*.java`
- `flight.reservation.flight.FlightOrder.java`

### Why Did We Use the Adapter Design Pattern?
- There were different payment functionalities in the codebase.
- Dedicated functions were written for each payment method, making the code difficult to maintain and extend.
- This also caused high coupling, making changes cumbersome.
- To overcome these issues, we use the **Adapter Design Pattern**, which helps standardize interfaces for different payment methods.

### How Did We Use the Adapter Design Pattern?
- We created an interface for the `PaymentGateway`.
- This interface defines the basic functionality required for all adapter classes.
- Each adapter class implements this interface according to the specific payment method.
- We implemented `CreditCardAdapter` and `PaypalAdapter`, encapsulating payment processing logic.
- From the client side, users provide the payment method as input, instantiate the appropriate adapter, and use the `pay()` function, regardless of the underlying implementation.

### Code Changes Made:
```java
// PaymentGateway interface
public interface PaymentGateway {
    public boolean pay(double amount);
}

// Paypal adapter
public class PaypalAdapter implements PaymentGateway {
    private PaypalInstance paypalInstance;
    
    public PaypalAdapter(PaypalInstance paypal) {
        this.paypalInstance = paypal;
    }
    
    @Override
    public boolean pay(double amount) {
        // Process payment with PayPal
        System.out.println("Paying " + amount + " using PayPal.");
        return true;
    }
}

// CreditCard adapter
public class CreditCardAdapter implements PaymentGateway {
    private CreditCard creditCard;
    
    public CreditCardAdapter(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
    
    @Override
    public boolean pay(double amount) {
        // Process payment with Credit Card
        System.out.println("Paying " + amount + " using Credit Card.");
        double remainingAmount = this.creditCard.getAmount() - amount;
        if (remainingAmount < 0) {
            System.out.printf("Card limit reached - Balance: %f%n", remainingAmount);
            throw new IllegalStateException("Card limit reached");
        }
        this.creditCard.setAmount(remainingAmount);
        return true;
    }
}

// Paying through Credit Card
public boolean payWithCreditCard(CreditCard card, double amount) throws IllegalStateException {
    if (cardIsPresentAndValid(card)) {
        // Pay
        PaymentGateway paymentGateway = new CreditCardAdapter(card);
        return paymentGateway.pay(amount);
    } else {
        return false;
    }
}

// Paying through PayPal
public boolean payWithPayPal(String email, String password, double amount) throws IllegalStateException {
    if (email.equals(Paypal.DATA_BASE.get(password))) {
        // Pay
        PaymentGateway paymentGateway = new PaypalAdapter(new PaypalInstance(email, password));
        return paymentGateway.pay(amount);
    } else {
        return false;
    }
}
```
