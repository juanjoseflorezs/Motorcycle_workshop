package co.edu.umanizales.motorcycle_workshop.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract class representing a generic vehicle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Motorcycle.class, name = "Motorcycle"),
    @JsonSubTypes.Type(value = Car.class, name = "Car"),
    @JsonSubTypes.Type(value = Truck.class, name = "Truck")
})
public abstract class Vehicle {
    private String id;
    private String brand;
    private String model;
    private int year;
    private String color;
    private double price;
    // Optional owner reference: clientId of the owner
    private String ownerClientId;

    /**
     * Abstract method that must be implemented by subclasses
     */
    public abstract String getVehicleType();

    /**
     * Abstract method to calculate maintenance cost
     */
    public abstract double calculateMaintenanceCost();

    /**
     * Common method for all vehicles
     */
    public String getFullDescription() {
        return String.format("%s - %s %s (%d) - Color: %s - Price: $%,.2f",
                getVehicleType(), brand, model, year, color, price);
    }

    // Convenience constructor to keep backward compatibility with subclasses
    public Vehicle(String id, String brand, String model, int year, String color, double price) {
        this(id, brand, model, year, color, price, null);
    }
}
