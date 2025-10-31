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
    private Double loadCapacityTons;
    private Integer numberOfAxles;
    private String cargoType;

    /**
     * Constructor with parent class parameters
     */
    public Truck(String id, String brand, String model, Integer year, 
                 String color, Double price, Double loadCapacityTons, 
                 Integer numberOfAxles, String cargoType) {
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
    public Double calculateMaintenanceCost() {
        // Maintenance cost based on load capacity and axles
        Double baseCost = 200000.0; // Base cost in local currency
        Double loadMultiplier = loadCapacityTons * 5000;
        Double axleMultiplier = numberOfAxles * 15000.0;
        return baseCost + loadMultiplier + axleMultiplier;
    }

    @Override
    public String getFullDescription() {
        return super.getFullDescription() + String.format(" - Capacity: %.1f tons - Axles: %d - Cargo: %s", 
            loadCapacityTons, numberOfAxles, cargoType);
    }
}
