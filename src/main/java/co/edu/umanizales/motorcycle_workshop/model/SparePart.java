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
    private String partName;
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
    public Boolean isAvailable() {
        return quantityInStock > 0;
    }

    /**
     * Check if stock is low
     */
    public Boolean isLowStock() {
        return quantityInStock <= minimumStock;
    }

    /**
     * Update stock quantity
     */
    public void updateStock(Integer quantity) {
        this.quantityInStock += quantity;
        if (this.quantityInStock < 0) {
            this.quantityInStock = 0;
        }
    }

    /**
     * Get total value of this part in inventory
     */
    public Double getTotalValue() {
        return unitPrice * quantityInStock;
    }

    /**
     * Get part information
     */
    public String getPartInfo() {
        return String.format("Part: %s (%s) - Brand: %s - Price: %.2f - Stock: %d - Location: %s",
            partName, partId, brand, unitPrice, quantityInStock, location);
    }

    /**
     * Get part summary for display
     */
    public String getSummary() {
        String stockStatus = isLowStock() ? "LOW STOCK" : "OK";
        return String.format("%s - %s - Unit Price: %.2f - Qty: %d (%s)",
            partName, brand, unitPrice, quantityInStock, stockStatus);
    }
}
