package co.edu.umanizales.motorcycle_workshop.controller;

import co.edu.umanizales.motorcycle_workshop.model.SparePart;
import co.edu.umanizales.motorcycle_workshop.service.SparePartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spare-parts")
public class SparePartController {

    private final SparePartService sparePartService;

    public SparePartController(SparePartService sparePartService) {
        this.sparePartService = sparePartService;
    }

    @GetMapping
    public ResponseEntity<List<SparePart>> getAll() {
        return ResponseEntity.ok(sparePartService.getAllSpareParts());
    }

    @GetMapping("/{partId}")
    public ResponseEntity<SparePart> getById(@PathVariable String partId) {
        return sparePartService.getSparePartById(partId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SparePart> create(@RequestBody SparePart part) {
        sparePartService.addSparePart(part);
        return ResponseEntity.ok(part);
    }

    @PutMapping("/{partId}")
    public ResponseEntity<Void> update(@PathVariable String partId, @RequestBody SparePart part) {
        boolean ok = sparePartService.updateSparePart(partId, part);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{partId}")
    public ResponseEntity<Void> delete(@PathVariable String partId) {
        boolean ok = sparePartService.deleteSparePart(partId);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<SparePart>> available() {
        return ResponseEntity.ok(sparePartService.getAvailableParts());
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<SparePart>> lowStock() {
        return ResponseEntity.ok(sparePartService.getLowStockParts());
    }

    @PatchMapping("/{partId}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable String partId, @RequestParam Integer quantity) {
        boolean ok = sparePartService.updatePartStock(partId, quantity);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-vehicle-type")
    public ResponseEntity<List<SparePart>> byVehicleType(@RequestParam String vehicleType) {
        return ResponseEntity.ok(sparePartService.getPartsByVehicleType(vehicleType));
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<SparePart>> byCategory(@RequestParam String category) {
        return ResponseEntity.ok(sparePartService.getPartsByCategory(category));
    }

    @GetMapping("/by-brand")
    public ResponseEntity<List<SparePart>> byBrand(@RequestParam String brand) {
        return ResponseEntity.ok(sparePartService.getPartsByBrand(brand));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SparePart>> search(@RequestParam String q) {
        return ResponseEntity.ok(sparePartService.searchPartsByName(q));
    }

    @GetMapping("/by-price-range")
    public ResponseEntity<List<SparePart>> byPriceRange(@RequestParam Double min,
                                                         @RequestParam Double max) {
        return ResponseEntity.ok(sparePartService.getPartsByPriceRange(min, max));
    }

    @GetMapping("/stats/total-value")
    public ResponseEntity<Double> totalValue() {
        return ResponseEntity.ok(sparePartService.getTotalInventoryValue());
    }

    @GetMapping("/stats/total-quantity")
    public ResponseEntity<Integer> totalQuantity() {
        return ResponseEntity.ok(sparePartService.getTotalQuantity());
    }
}
