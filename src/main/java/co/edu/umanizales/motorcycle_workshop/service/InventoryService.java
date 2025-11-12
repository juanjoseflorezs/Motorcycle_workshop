package co.edu.umanizales.motorcycle_workshop.service;

import co.edu.umanizales.motorcycle_workshop.model.Inventory;
import co.edu.umanizales.motorcycle_workshop.model.SparePart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing inventory
 */
@Service
public class InventoryService {
    private List<Inventory> inventories = new ArrayList<>();

    /**
     * Create a new inventory
     */
    public void createInventory(Inventory inventory) {
        inventories.add(inventory);
    }

    /**
     * Get all inventories
     */
    public List<Inventory> getAllInventories() {
        return new ArrayList<>(inventories);
    }

    /**
     * Get inventory by ID
     */
    public Optional<Inventory> getInventoryById(String inventoryId) {
        return inventories.stream()
                .filter(i -> i.getInventoryId().equals(inventoryId))
                .findFirst();
    }

    /**
     * Add spare part to inventory
     */
    public boolean addPartToInventory(String inventoryId, SparePart part) {
        Optional<Inventory> inventory = getInventoryById(inventoryId);
        if (inventory.isPresent()) {
            inventory.get().addPart(part);
            return true;
        }
        return false;
    }

    /**
     * Remove spare part from inventory
     */
    public boolean removePartFromInventory(String inventoryId, String partId) {
        Optional<Inventory> inventory = getInventoryById(inventoryId);
        if (inventory.isPresent()) {
            return inventory.get().removePart(partId);
        }
        return false;
    }

    /**
     * Update part stock in inventory
     */
    public boolean updatePartStock(String inventoryId, String partId, Integer quantity) {
        Optional<Inventory> inventory = getInventoryById(inventoryId);
        if (inventory.isPresent()) {
            return inventory.get().updatePartStock(partId, quantity);
        }
        return false;
    }

    /**
     * Get part from inventory
     */
    public Optional<SparePart> getPartFromInventory(String inventoryId, String partId) {
        Optional<Inventory> inventory = getInventoryById(inventoryId);
        if (inventory.isPresent()) {
            return Optional.ofNullable(inventory.get().getPartById(partId));
        }
        return Optional.empty();
    }

    /**
     * Get low stock items from inventory
     */
    public List<SparePart> getLowStockItems(String inventoryId) {
        Optional<Inventory> inventory = getInventoryById(inventoryId);
        if (inventory.isPresent()) {
            return inventory.get().getLowStockItems();
        }
        return new ArrayList<>();
    }

    /**
     * Get parts by vehicle type from inventory
     */
    public List<SparePart> getPartsByVehicleType(String inventoryId, String vehicleType) {
        Optional<Inventory> inventory = getInventoryById(inventoryId);
        if (inventory.isPresent()) {
            return inventory.get().getPartsByVehicleType(vehicleType);
        }
        return new ArrayList<>();
    }

    /**
     * Get parts by category from inventory
     */
    public List<SparePart> getPartsByCategory(String inventoryId, String category) {
        Optional<Inventory> inventory = getInventoryById(inventoryId);
        if (inventory.isPresent()) {
            return inventory.get().getPartsByCategory(category);
        }
        return new ArrayList<>();
    }

    /**
     * Get total inventory value
     */
    public Double getTotalInventoryValue(String inventoryId) {
        Optional<Inventory> inventory = getInventoryById(inventoryId);
        if (inventory.isPresent()) {
            return inventory.get().getTotalInventoryValue();
        }
        return 0.0;
    }

    /**
     * Get total part types in inventory
     */
    public Integer getTotalPartTypes(String inventoryId) {
        Optional<Inventory> inventory = getInventoryById(inventoryId);
        if (inventory.isPresent()) {
            return inventory.get().getTotalPartTypes();
        }
        return 0;
    }

    /**
     * Get total quantity in inventory
     */
    public Integer getTotalQuantity(String inventoryId) {
        Optional<Inventory> inventory = getInventoryById(inventoryId);
        if (inventory.isPresent()) {
            return inventory.get().getTotalQuantity();
        }
        return 0;
    }

    /**
     * Generate inventory report
     */
    public String generateInventoryReport(String inventoryId) {
        Optional<Inventory> inventory = getInventoryById(inventoryId);
        if (inventory.isPresent()) {
            return inventory.get().generateReport();
        }
        return "Inventory not found";
    }

    /**
     * Get inventory summary
     */
    public String getInventorySummary(String inventoryId) {
        Optional<Inventory> inventory = getInventoryById(inventoryId);
        if (inventory.isPresent()) {
            return inventory.get().getSummary();
        }
        return "Inventory not found";
    }

    /**
     * Delete inventory
     */
    public boolean deleteInventory(String inventoryId) {
        return inventories.removeIf(i -> i.getInventoryId().equals(inventoryId));
    }

    /**
     * Get total value across all inventories
     */
    public Double getTotalValueAllInventories() {
        return inventories.stream()
                .mapToDouble(Inventory::getTotalInventoryValue)
                .sum();
    }

    /**
     * Get total parts across all inventories
     */
    public Integer getTotalPartsAllInventories() {
        return inventories.stream()
                .mapToInt(Inventory::getTotalQuantity)
                .sum();
    }
}
