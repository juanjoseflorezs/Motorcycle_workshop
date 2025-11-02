package co.edu.umanizales.motorcycle_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing a Spare Part available in the workshop
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SparePart {
    private String partId;
    private String name; // Cambiado de partName a name para consistencia
    private String brand;
    private String category;
    private Double unitPrice;
    private Integer quantityInStock;
    private Integer minimumStock;
    private String vehicleType;
    private String supplier;
    private String location;

    /**
     * Check if part is available in stock
     */
    public boolean isAvailable() {
        return quantityInStock != null && quantityInStock > 0;
    }

    /**
     * Check if stock is low
     */
    public boolean isLowStock() {
        return minimumStock != null && quantityInStock != null && quantityInStock <= minimumStock;
    }

    /**
     * Update stock quantity
     */
    public void updateStock(int quantity) {
        if (quantityInStock != null) {
            this.quantityInStock += quantity;
            if (this.quantityInStock < 0) {
                this.quantityInStock = 0;
            }
        }
    }

    /**
     * Get total value of this part in inventory
     */
    /**
     * Calculate total value of this part in inventory
     * @return total value (unitPrice * quantityInStock)
     */
    public double getTotalValue() {
        if (unitPrice == null || quantityInStock == null) {
            return 0.0;
        }
        return unitPrice * quantityInStock;
    }

    /**
     * Get part information
     */
    public String getSummary() {
        return String.format("Part: %s - %s - Stock: %d - Price: $%,.2f - %s",
            partId, name, 
            quantityInStock != null ? quantityInStock : 0, 
            unitPrice != null ? unitPrice : 0.0,
            isLowStock() ? "[LOW STOCK]" : "");
    }
    
    // Getters adicionales necesarios para Inventory
    public String getDescription() {
        return String.format("%s %s (%s)", name, brand, category);
    }
    
    public double getPrice() {
        return unitPrice != null ? unitPrice : 0.0;
    }
    
    public String getCompatibleVehicleType() {
        return vehicleType != null ? vehicleType : "UNIVERSAL";
    }
}
