package flight.reservation.plane;

public class PassengerDrone implements vehicle {
    private final String model;
    public int crewCapacity = 0;

    public PassengerDrone(String model) {
        if (model.equals("HypaHype")) {
            this.model = model;
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
        return 4;
    }
}
