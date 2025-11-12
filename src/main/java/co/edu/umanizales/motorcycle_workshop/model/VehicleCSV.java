package co.edu.umanizales.motorcycle_workshop.model;

/**
 * Wrapper class to make Vehicle CSV-saveable
 * Implements the Saveable interface
 */
public class VehicleCSV implements Saveable {
    private Vehicle vehicle;

    public VehicleCSV(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toCSV() {
        if (vehicle instanceof Motorcycle) {
            Motorcycle moto = (Motorcycle) vehicle;
            return String.format("%s,%s,%s,%d,%s,%.2f,%s,%d,%s,%s",
                moto.getId(),
                moto.getBrand(),
                moto.getModel(),
                moto.getYear(),
                moto.getColor(),
                moto.getPrice(),
                moto.getVehicleType(),
                moto.getEngineCC(),
                moto.getTransmissionType(),
                moto.setHasWindshield () );
        } else if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            return String.format("%s,%s,%s,%d,%s,%.2f,%s,%d,%s,%.1f",
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getColor(),
                car.getPrice(),
                car.getVehicleType(),
                car.getNumberOfDoors(),
                car.getFuelType(),
                car.getFuelConsumption());
        } else if (vehicle instanceof Truck) {
            Truck truck = (Truck) vehicle;
            return String.format("%s,%s,%s,%d,%s,%.2f,%s,%.1f,%d,%s",
                truck.getId(),
                truck.getBrand(),
                truck.getModel(),
                truck.getYear(),
                truck.getColor(),
                truck.getPrice(),
                truck.getVehicleType(),
                truck.getLoadCapacityTons(),
                truck.getNumberOfAxles(),
                truck.getCargoType());
        }
        return "";
    }

    @Override
    public String getCSVHeader() {
        if (vehicle instanceof Motorcycle) {
            return "ID,Brand,Model,Year,Color,Price,Type,EngineCC,TransmissionType,HasWindshield";
        } else if (vehicle instanceof Car) {
            return "ID,Brand,Model,Year,Color,Price,Type,NumberOfDoors,FuelType,FuelConsumption";
        } else if (vehicle instanceof Truck) {
            return "ID,Brand,Model,Year,Color,Price,Type,LoadCapacityTons,NumberOfAxles,CargoType";
        }
        return "";
    }
}
