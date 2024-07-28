interface Vehicle {
    void drive();
}

class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a car");
    }
}

class Truck implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a truck");
    }
}

class Motorcycle implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a motorcycle");
    }
}

class VehicleFactory {
    public static Vehicle createVehicle(String vehicleType) {
        if (vehicleType.equals("car")) {
            return new Car();
        } else if (vehicleType.equals("truck")) {
            return new Truck();
        } else if (vehicleType.equals("motorcycle")) {
            return new Motorcycle();
        } else {
            throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}

public class Factory_Pattern{
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.createVehicle("car");
        car.drive();

        Vehicle truck = VehicleFactory.createVehicle("truck");
        truck.drive();

        Vehicle motorcycle = VehicleFactory.createVehicle("motorcycle");
        motorcycle.drive();
    }
}