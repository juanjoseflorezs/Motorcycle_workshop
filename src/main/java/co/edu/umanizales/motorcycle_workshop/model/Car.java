package co.edu.umanizales.motorcycle_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Concrete class representing a Car
 * Inherits from Vehicle abstract class
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car extends Vehicle {
    private int numberOfDoors;
    private String fuelType;
    private double fuelConsumption;

    /**
     * Constructor with parent class parameters
     */
    public Car(String id, String brand, String model, int year, 
               String color, double price, int numberOfDoors, 
               String fuelType, double fuelConsumption) {
        super(id, brand, model, year, color, price);
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    @Override
    public double calculateMaintenanceCost() {
        // Maintenance cost based on fuel consumption
        double baseCost = 100000.0; // Base cost in local currency
        double consumptionMultiplier = fuelConsumption * 10;
        return baseCost + consumptionMultiplier;
    }

    @Override
    public String getFullDescription() {
        return String.format("%s - Doors: %d - Fuel: %s (%.1f L/100km)", 
            super.getFullDescription(), numberOfDoors, fuelType, fuelConsumption);
    }

    @Override
    public Object setHasWindshield() {
        return null;
    }
}
