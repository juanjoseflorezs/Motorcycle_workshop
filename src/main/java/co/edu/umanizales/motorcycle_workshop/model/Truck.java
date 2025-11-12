package co.edu.umanizales.motorcycle_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Concrete class representing a Truck
 * Inherits from Vehicle abstract class
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Truck extends Vehicle {
    private double loadCapacityTons;
    private int numberOfAxles;
    private String cargoType;

    /**
     * Constructor with parent class parameters
     */
    public Truck(String id, String brand, String model, int year, 
                 String color, double price, double loadCapacityTons, 
                 int numberOfAxles, String cargoType) {
        super(id, brand, model, year, color, price);
        this.loadCapacityTons = loadCapacityTons;
        this.numberOfAxles = numberOfAxles;
        this.cargoType = cargoType;
    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }

    @Override
    public double calculateMaintenanceCost() {
        // Maintenance cost based on load capacity and axles
        double baseCost = 200000.0; // Base cost in local currency
        double loadMultiplier = loadCapacityTons * 5000;
        double axleMultiplier = numberOfAxles * 15000.0;
        return baseCost + loadMultiplier + axleMultiplier;
    }

    @Override
    public String getFullDescription() {
        return String.format("%s - Capacity: %.1f tons - Axles: %d - Cargo: %s", 
            super.getFullDescription(), loadCapacityTons, numberOfAxles, cargoType);
    }

    @Override
    public Object setHasWindshield() {
        return null;
    }
}
