package co.edu.umanizales.motorcycle_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract class representing a generic vehicle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Vehicle {
    private String id;
    private String brand;
    private String model;
    private Integer year;
    private String color;
    private Double price;

    /**
     * Abstract method that must be implemented by subclasses
     */
    public abstract String getVehicleType();

    /**
     * Abstract method to calculate maintenance cost
     */
    public abstract Double calculateMaintenanceCost();

    /**
     * Common method for all vehicles
     */
    public String getFullDescription() {
        return String.format("%s - %s %s (%d) - Color: %s", 
            getVehicleType(), brand, model, year, color);
    }
}
