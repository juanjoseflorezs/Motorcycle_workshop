package co.edu.umanizales.motorcycle_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class representing the Inventory of the workshop
 * Manages all spare parts in stock
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    private String inventoryId;
    private List<SparePart> spareParts;
    private String lastUpdated;
    private String warehouseLocation;

    /**
     * Constructor with inventory ID
     */
    public Inventory(String inventoryId, String warehouseLocation) {
        this.inventoryId = inventoryId;
        this.warehouseLocation = warehouseLocation;
        this.spareParts = new ArrayList<>();
    }

    /**
     * Add a spare part to inventory
     */
    public void addPart(SparePart part) {
        Optional<SparePart> existing = spareParts.stream()
                .filter(p -> p.getPartId().equals(part.getPartId()))
                .findFirst();

        if (existing.isPresent()) {
            existing.get().updateStock(part.getQuantityInStock());
        } else {
            spareParts.add(part);
        }
    }

    /**
     * Remove a spare part from inventory
     */
    public boolean removePart(String partId) {
        return spareParts.removeIf(p -> p.getPartId().equals(partId));
    }

    /**
     * Get part by ID
     */
    public Optional<SparePart> getPartById(String partId) {
        return spareParts.stream()
                .filter(p -> p.getPartId().equals(partId))
                .findFirst();
    }

    /**
     * Get all low stock items
     */
    public List<SparePart> getLowStockItems() {
        List<SparePart> lowStockItems = new ArrayList<>();
        for (SparePart part : spareParts) {
            if (part.isLowStock()) {
                lowStockItems.add(part);
            }
        }
        return lowStockItems;
    }

    /**
     * Get parts by vehicle type
     */
    public List<SparePart> getPartsByVehicleType(String vehicleType) {
        List<SparePart> result = new ArrayList<>();
        for (SparePart part : spareParts) {
            if (part.getVehicleType().equalsIgnoreCase(vehicleType)) {
                result.add(part);
            }
        }
        return result;
    }

    /**
     * Get parts by category
     */
    public List<SparePart> getPartsByCategory(String category) {
        List<SparePart> result = new ArrayList<>();
        for (SparePart part : spareParts) {
            if (part.getCategory().equalsIgnoreCase(category)) {
                result.add(part);
            }
        }
        return result;
    }

    /**
     * Calculate total inventory value
     */
    public Double getTotalInventoryValue() {
        return spareParts.stream()
                .mapToDouble(SparePart::getTotalValue)
                .sum();
    }

    /**
     * Get number of different parts
     */
    public Integer getTotalPartTypes() {
        return spareParts.size();
    }

    /**
     * Get total quantity of all parts
     */
    public Integer getTotalQuantity() {
        return spareParts.stream()
                .mapToInt(SparePart::getQuantityInStock)
                .sum();
    }

    /**
     * Update part stock
     */
    public boolean updatePartStock(String partId, Integer quantity) {
        Optional<SparePart> part = getPartById(partId);
        if (part.isPresent()) {
            part.get().updateStock(quantity);
            return true;
        }
        return false;
    }

    /**
     * Generate inventory report
     */
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== INVENTORY REPORT ===\n");
        report.append("Inventory ID: ").append(inventoryId).append("\n");
        report.append("Location: ").append(warehouseLocation).append("\n");
        report.append("Total Part Types: ").append(getTotalPartTypes()).append("\n");
        report.append("Total Quantity: ").append(getTotalQuantity()).append("\n");
        report.append("Total Value: ").append(String.format("%.2f", getTotalInventoryValue())).append("\n");
        report.append("Low Stock Items: ").append(getLowStockItems().size()).append("\n");
        report.append("\n--- PARTS ---\n");

        for (SparePart part : spareParts) {
            report.append(part.getSummary()).append("\n");
        }

        return report.toString();
    }

    /**
     * Get inventory summary
     */
    public String getSummary() {
        return String.format("Inventory %s - Parts: %d - Total Qty: %d - Value: %.2f - Low Stock: %d",
            inventoryId, getTotalPartTypes(), getTotalQuantity(),
            getTotalInventoryValue(), getLowStockItems().size());
    }
}
