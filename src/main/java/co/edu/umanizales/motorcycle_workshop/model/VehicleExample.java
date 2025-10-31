package co.edu.umanizales.motorcycle_workshop.model;

/**
 * Example class demonstrating the use of the Vehicle model
 * This class shows how inheritance, polymorphism, and abstraction work
 */
public class VehicleExample {

    public static void main(String[] args) {
        // Create instances of different vehicle types
        Motorcycle motorcycle = new Motorcycle(
            "MOTO001",
            "Yamaha",
            "YZF-R1",
            2023,
            "Rojo",
            15000000.0,
            998,
            "Manual",
            true
        );

        Car car = new Car(
            "CAR001",
            "Toyota",
            "Corolla",
            2023,
            "Blanco",
            35000000.0,
            4,
            "Gasolina",
            7.5
        );

        Truck truck = new Truck(
            "TRUCK001",
            "Volvo",
            "FH16",
            2022,
            "Azul",
            80000000.0,
            25.0,
            3,
            "General"
        );

        // Demonstrate polymorphism - same method, different behavior
        System.out.println("=== VEHICLE INFORMATION ===\n");
        printVehicleInfo(motorcycle);
        printVehicleInfo(car);
        printVehicleInfo(truck);

        // Demonstrate maintenance cost calculation
        System.out.println("\n=== MAINTENANCE COSTS ===\n");
        System.out.println("Motorcycle maintenance: " + motorcycle.calculateMaintenanceCost());
        System.out.println("Car maintenance: " + car.calculateMaintenanceCost());
        System.out.println("Truck maintenance: " + truck.calculateMaintenanceCost());

        // Calculate total maintenance
        double totalMaintenance = motorcycle.calculateMaintenanceCost() +
                                 car.calculateMaintenanceCost() +
                                 truck.calculateMaintenanceCost();
        System.out.println("Total maintenance cost: " + totalMaintenance);

        // Demonstrate CSV serialization
        System.out.println("\n=== CSV SERIALIZATION ===\n");
        VehicleCSV motoCSV = new VehicleCSV(motorcycle);
        VehicleCSV carCSV = new VehicleCSV(car);
        VehicleCSV truckCSV = new VehicleCSV(truck);

        System.out.println(motoCSV.getCSVHeader());
        System.out.println(motoCSV.toCSV());
        System.out.println();
        System.out.println(carCSV.getCSVHeader());
        System.out.println(carCSV.toCSV());
        System.out.println();
        System.out.println(truckCSV.getCSVHeader());
        System.out.println(truckCSV.toCSV());
    }

    /**
     * Helper method to print vehicle information
     * Demonstrates polymorphism - same method works with different types
     */
    private static void printVehicleInfo(Vehicle vehicle) {
        System.out.println("Type: " + vehicle.getVehicleType());
        System.out.println("Description: " + vehicle.getFullDescription());
        System.out.println("Price: " + vehicle.getPrice());
        System.out.println("---");
    }
}
