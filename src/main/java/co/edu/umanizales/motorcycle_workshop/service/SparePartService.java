package co.edu.umanizales.motorcycle_workshop.service;

import co.edu.umanizales.motorcycle_workshop.model.SparePart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing spare parts
 */
@Service
public class SparePartService {
    private List<SparePart> spareParts = new ArrayList<>();

    /**
     * Add a new spare part
     */
    public void addSparePart(SparePart part) {
        spareParts.add(part);
    }

    /**
     * Get all spare parts
     */
    public List<SparePart> getAllSpareParts() {
        return new ArrayList<>(spareParts);
    }

    /**
     * Get spare part by ID
     */
    public Optional<SparePart> getSparePartById(String partId) {
        return spareParts.stream()
                .filter(p -> p.getPartId().equals(partId))
                .findFirst();
    }

    /**
     * Get available parts
     */
    public List<SparePart> getAvailableParts() {
        List<SparePart> available = new ArrayList<>();
        for (SparePart part : spareParts) {
            if (part.isAvailable()) {
                available.add(part);
            }
        }
        return available;
    }

    /**
     * Get low stock parts
     */
    public List<SparePart> getLowStockParts() {
        List<SparePart> lowStock = new ArrayList<>();
        for (SparePart part : spareParts) {
            if (part.isLowStock()) {
                lowStock.add(part);
            }
        }
        return lowStock;
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
     * Get parts by brand
     */
    public List<SparePart> getPartsByBrand(String brand) {
        List<SparePart> result = new ArrayList<>();
        for (SparePart part : spareParts) {
            if (part.getBrand().equalsIgnoreCase(brand)) {
                result.add(part);
            }
        }
        return result;
    }

    /**
     * Update part stock
     */
    public boolean updatePartStock(String partId, Integer quantity) {
        Optional<SparePart> part = getSparePartById(partId);
        if (part.isPresent()) {
            part.get().updateStock(quantity);
            return true;
        }
        return false;
    }

    /**
     * Update part information
     */
    public boolean updateSparePart(String partId, SparePart updatedPart) {
        Optional<SparePart> existing = getSparePartById(partId);
        if (existing.isPresent()) {
            int index = spareParts.indexOf(existing.get());
            spareParts.set(index, updatedPart);
            return true;
        }
        return false;
    }

    /**
     * Delete spare part
     */
    public boolean deleteSparePart(String partId) {
        return spareParts.removeIf(p -> p.getPartId().equals(partId));
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
     * Get total quantity of all parts
     */
    public Integer getTotalQuantity() {
        return spareParts.stream()
                .mapToInt(SparePart::getQuantityInStock)
                .sum();
    }

    /**
     * Search parts by name
     */
    public List<SparePart> searchPartsByName(String name) {
        List<SparePart> result = new ArrayList<>();
        String searchTerm = name.toLowerCase();
        for (SparePart part : spareParts) {
            if (part.getPartName().toLowerCase().contains(searchTerm)) {
                result.add(part);
            }
        }
        return result;
    }

    /**
     * Get parts above certain price
     */
    public List<SparePart> getPartsByPriceRange(Double minPrice, Double maxPrice) {
        List<SparePart> result = new ArrayList<>();
        for (SparePart part : spareParts) {
            if (part.getUnitPrice() >= minPrice && part.getUnitPrice() <= maxPrice) {
                result.add(part);
            }
        }
        return result;
    }
}
