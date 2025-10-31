package co.edu.umanizales.motorcycle_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Concrete class representing a Motorcycle
 * Inherits from Vehicle abstract class
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Motorcycle extends Vehicle {
    private Integer engineCC;
    private String transmissionType;
    private Boolean hasWindshield;

    /**
     * Constructor with parent class parameters
     */
    public Motorcycle(String id, String brand, String model, Integer year, 
                     String color, Double price, Integer engineCC, 
                     String transmissionType, Boolean hasWindshield) {
        super(id, brand, model, year, color, price);
        this.engineCC = engineCC;
        this.transmissionType = transmissionType;
        this.hasWindshield = hasWindshield;
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }

    @Override
    public Double calculateMaintenanceCost() {
        // Maintenance cost based on engine CC
        Double baseCost = 50000.0; // Base cost in local currency
        Double ccMultiplier = engineCC / 100.0;
        return baseCost * ccMultiplier;
    }

    @Override
    public String getFullDescription() {
        return super.getFullDescription() + String.format(" - Engine: %dCC - Transmission: %s", 
            engineCC, transmissionType);
    }
}
