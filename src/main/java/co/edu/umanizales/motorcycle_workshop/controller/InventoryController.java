package co.edu.umanizales.motorcycle_workshop.controller;

import co.edu.umanizales.motorcycle_workshop.model.Inventory;
import co.edu.umanizales.motorcycle_workshop.model.SparePart;
import co.edu.umanizales.motorcycle_workshop.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Inventories
    @GetMapping
    public ResponseEntity<List<Inventory>> getAll() {
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<Inventory> getById(@PathVariable String inventoryId) {
        return inventoryService.getInventoryById(inventoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Inventory> create(@RequestBody Inventory inventory) {
        inventoryService.createInventory(inventory);
        return ResponseEntity.ok(inventory);
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<Void> delete(@PathVariable String inventoryId) {
        boolean ok = inventoryService.deleteInventory(inventoryId);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Parts within a given inventory
    @PostMapping("/{inventoryId}/parts")
    public ResponseEntity<Void> addPart(@PathVariable String inventoryId, @RequestBody SparePart part) {
        boolean ok = inventoryService.addPartToInventory(inventoryId, part);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{inventoryId}/parts/{partId}")
    public ResponseEntity<Void> removePart(@PathVariable String inventoryId, @PathVariable String partId) {
        boolean ok = inventoryService.removePartFromInventory(inventoryId, partId);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{inventoryId}/parts/{partId}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable String inventoryId,
                                            @PathVariable String partId,
                                            @RequestParam Integer quantity) {
        boolean ok = inventoryService.updatePartStock(inventoryId, partId, quantity);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{inventoryId}/parts/{partId}")
    public ResponseEntity<SparePart> getPart(@PathVariable String inventoryId, @PathVariable String partId) {
        return inventoryService.getPartFromInventory(inventoryId, partId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{inventoryId}/parts/low-stock")
    public ResponseEntity<List<SparePart>> lowStock(@PathVariable String inventoryId) {
        return ResponseEntity.ok(inventoryService.getLowStockItems(inventoryId));
    }

    @GetMapping("/{inventoryId}/parts/by-vehicle-type")
    public ResponseEntity<List<SparePart>> byVehicleType(@PathVariable String inventoryId,
                                                         @RequestParam String vehicleType) {
        return ResponseEntity.ok(inventoryService.getPartsByVehicleType(inventoryId, vehicleType));
    }

    @GetMapping("/{inventoryId}/parts/by-category")
    public ResponseEntity<List<SparePart>> byCategory(@PathVariable String inventoryId,
                                                      @RequestParam String category) {
        return ResponseEntity.ok(inventoryService.getPartsByCategory(inventoryId, category));
    }

    // Stats and reports
    @GetMapping("/{inventoryId}/stats/total-value")
    public ResponseEntity<Double> totalValue(@PathVariable String inventoryId) {
        return ResponseEntity.ok(inventoryService.getTotalInventoryValue(inventoryId));
    }

    @GetMapping("/{inventoryId}/stats/total-types")
    public ResponseEntity<Integer> totalTypes(@PathVariable String inventoryId) {
        return ResponseEntity.ok(inventoryService.getTotalPartTypes(inventoryId));
        
    }

    @GetMapping("/{inventoryId}/stats/total-quantity")
    public ResponseEntity<Integer> totalQuantity(@PathVariable String inventoryId) {
        return ResponseEntity.ok(inventoryService.getTotalQuantity(inventoryId));
    }

    @GetMapping("/{inventoryId}/report")
    public ResponseEntity<String> report(@PathVariable String inventoryId) {
        return ResponseEntity.ok(inventoryService.generateInventoryReport(inventoryId));
    }

    @GetMapping("/{inventoryId}/summary")
    public ResponseEntity<String> summary(@PathVariable String inventoryId) {
        return ResponseEntity.ok(inventoryService.getInventorySummary(inventoryId));
    }
}
