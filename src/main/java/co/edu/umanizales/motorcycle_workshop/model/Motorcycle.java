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
    private int engineCC;
    private String transmissionType;
    private boolean hasWindshield;

    /**
     * Constructor with parent class parameters
     */
    public Motorcycle(String id, String brand, String model, int year, 
                     String color, double price, int engineCC, 
                     String transmissionType, boolean hasWindshield) {
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
    public double calculateMaintenanceCost() {
        // Maintenance cost based on engine CC
        double baseCost = 50000.0; // Base cost in local currency
        double ccMultiplier = engineCC / 100.0;
        return baseCost * ccMultiplier;
    }

    @Override
    public String getFullDescription() {
        return String.format("%s - Engine: %dCC - Transmission: %s - Windshield: %s", 
            super.getFullDescription(), 
            engineCC, 
            transmissionType != null ? transmissionType : "N/A", 
            hasWindshield ? "Yes" : "No");
    }

    @Override
    public Object setHasWindshield() {
        return null;
    }
}
