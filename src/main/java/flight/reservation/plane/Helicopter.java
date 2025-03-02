package flight.reservation.plane;

public class Helicopter implements vehicle {
    private final String model;
    private final int passengerCapacity;
    public int crewCapacity = 2;

    public Helicopter(String model) {
        this.model = model;
        if (model.equals("H1")) {
            passengerCapacity = 4;
        } else if (model.equals("H2")) {
            passengerCapacity = 6;
        } else {
            throw new IllegalArgumentException(String.format("Model type '%s' is not recognized", model));
        }
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getcrewCapacity() {
        return crewCapacity;
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }
}
