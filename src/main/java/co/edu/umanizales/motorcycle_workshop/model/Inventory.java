package co.edu.umanizales.motorcycle_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Class representing the Inventory of the workshop
 * Manages all spare parts in stock
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory implements Saveable {
    private String inventoryId;
    private List<SparePart> spareParts = new ArrayList<>();
    private String lastUpdated;
    private String warehouseLocation;

    /**
     * Constructor with inventory ID
     */
    public Inventory(String inventoryId, String warehouseLocation) {
        if (inventoryId == null || inventoryId.trim().isEmpty()) {
            throw new IllegalArgumentException("Inventory ID cannot be null or empty");
        }
        this.inventoryId = inventoryId.trim();
        this.warehouseLocation = warehouseLocation != null ? warehouseLocation.trim() : "";
        this.spareParts = new ArrayList<>();
    }

    /**
     * Add a spare part to inventory
     */
    public void addPart(SparePart part) {
        if (part == null) {
            throw new IllegalArgumentException("Part cannot be null");
        }
        
        boolean partExists = false;
        for (SparePart existing : spareParts) {
            if (existing != null && existing.getPartId() != null && 
                existing.getPartId().equals(part.getPartId())) {
                // Si la parte ya existe, actualiza el stock
                int newQuantity = existing.getQuantityInStock() + part.getQuantityInStock();
                existing.setQuantityInStock(newQuantity);
                partExists = true;
                break;
            }
        }
        
        if (!partExists) {
            spareParts.add(part);
        }
    }

    /**
     * Remove a spare part from inventory
     */
    public boolean removePart(String partId) {
        if (partId == null || partId.trim().isEmpty()) {
            throw new IllegalArgumentException("Part ID cannot be null or empty");
        }
        return spareParts.removeIf(p -> p.getPartId().equals(partId));
    }

    /**
     * Get part by ID
     * @param partId ID of the part to find
     * @return the SparePart if found, null otherwise
     */
    public SparePart getPartById(String partId) {
        if (partId == null || partId.trim().isEmpty()) {
            return null;
        }
        
        for (SparePart part : spareParts) {
            if (part != null && partId.equals(part.getPartId())) {
                return part;
            }
        }
        return null;
    }

    /**
     * Get all low stock items
     */
    public List<SparePart> getLowStockItems() {
        List<SparePart> lowStockItems = new ArrayList<>();
        final int LOW_STOCK_THRESHOLD = 5; // Umbral de bajo inventario
        
        for (SparePart part : spareParts) {
            if (part != null && part.getQuantityInStock() < LOW_STOCK_THRESHOLD) {
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
    public double getTotalInventoryValue() {
        return spareParts.stream()
                .mapToDouble(SparePart::getTotalValue)
                .sum();
    }

    /**
     * Get number of different parts
     */
    public int getTotalPartTypes() {
        return spareParts.size();
    }

    /**
     * Get total quantity of all parts
     */
    public int getTotalQuantity() {
        return spareParts.stream()
                .mapToInt(SparePart::getQuantityInStock)
                .sum();
    }

    /**
     * Update part stock
     */
    public boolean updatePartStock(String partId, int quantity) {
        SparePart part = getPartById(partId);
        if (part != null) {
            part.updateStock(quantity);
            return true;
        }
        return false;
    }

    /**
     * Generate inventory report
     */
    /**
     * Generate inventory report
     * @return String with the inventory report
     */
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== INVENTORY REPORT ===\n");
        report.append("Inventory ID: ").append(inventoryId).append("\n");
        report.append("Location: ").append(warehouseLocation).append("\n");
        report.append("Total Part Types: ").append(getTotalPartTypes()).append("\n");
        report.append("Total Quantity: ").append(getTotalQuantity()).append("\n");
        report.append("Total Value: ").append(String.format("$%,.2f", getTotalInventoryValue())).append("\n");
        
        List<SparePart> lowStockItems = getLowStockItems();
        report.append("Low Stock Items: ").append(lowStockItems.size()).append("\n");
        
        if (!lowStockItems.isEmpty()) {
            report.append("\n--- LOW STOCK ITEMS ---\n");
            for (SparePart part : lowStockItems) {
                report.append(part.getSummary()).append("\n");
            }
        }
        
        report.append("\n--- ALL PARTS ---\n");
        if (spareParts.isEmpty()) {
            report.append("No parts in inventory.\n");
        } else {
            for (SparePart part : spareParts) {
                report.append(part.getSummary()).append("\n");
            }
        }

        return report.toString();
    }

    /**
     * Get inventory summary
     */
    public String getSummary() {
        return String.format("Inventory: %s - Location: %s - Parts: %d - Last Updated: %s",
            inventoryId, warehouseLocation, spareParts.size(), 
            lastUpdated != null ? lastUpdated : "Never");
    }
    
    @Override
    public String toCSV() {
        // Implementación básica de CSV para Inventory
        StringBuilder csv = new StringBuilder();
        csv.append(getCSVHeader()).append("\n");
        for (SparePart part : spareParts) {
            csv.append(String.format("%s,%s,%s,%d,%.2f,%s\n",
                part.getPartId(),
                part.getName(),
                part.getDescription(),
                part.getQuantityInStock(),
                part.getPrice(),
                part.getCompatibleVehicleType()));
        }
        return csv.toString();
    }
    
    @Override
    public String getCSVHeader() {
        return "partId,name,description,quantityInStock,price,compatibleVehicleType";
    }
}
