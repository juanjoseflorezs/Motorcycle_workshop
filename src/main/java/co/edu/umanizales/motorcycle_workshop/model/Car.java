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
    private Integer numberOfDoors;
    private String fuelType;
    private Double fuelConsumption;

    /**
     * Constructor with parent class parameters
     */
    public Car(String id, String brand, String model, Integer year, 
               String color, Double price, Integer numberOfDoors, 
               String fuelType, Double fuelConsumption) {
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
    public Double calculateMaintenanceCost() {
        // Maintenance cost based on fuel consumption
        Double baseCost = 100000.0; // Base cost in local currency
        Double consumptionMultiplier = fuelConsumption * 10;
        return baseCost + consumptionMultiplier;
    }

    @Override
    public String getFullDescription() {
        return super.getFullDescription() + String.format(" - Doors: %d - Fuel: %s (%.1f L/100km)", 
            numberOfDoors, fuelType, fuelConsumption);
    }
}
