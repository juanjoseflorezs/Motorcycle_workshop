package co.edu.umanizales.motorcycle_workshop.controller;

import co.edu.umanizales.motorcycle_workshop.model.Maintenance;
import co.edu.umanizales.motorcycle_workshop.model.PartUsage;
import co.edu.umanizales.motorcycle_workshop.service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping
    public ResponseEntity<List<Maintenance>> getAll() {
        return ResponseEntity.ok(maintenanceService.getAllMaintenances());
    }

    @GetMapping("/{maintenanceId}")
    public ResponseEntity<Maintenance> getById(@PathVariable String maintenanceId) {
        return maintenanceService.getById(maintenanceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-vehicle/{vehicleId}")
    public ResponseEntity<List<Maintenance>> byVehicle(@PathVariable String vehicleId) {
        return ResponseEntity.ok(maintenanceService.getByVehicleId(vehicleId));
    }

    @GetMapping("/by-client/{clientId}")
    public ResponseEntity<List<Maintenance>> byClient(@PathVariable String clientId) {
        return ResponseEntity.ok(maintenanceService.getByClientId(clientId));
    }

    @GetMapping("/by-service/{serviceId}")
    public ResponseEntity<List<Maintenance>> byService(@PathVariable String serviceId) {
        return ResponseEntity.ok(maintenanceService.getByServiceId(serviceId));
    }

    @PostMapping
    public ResponseEntity<Maintenance> create(@RequestBody Maintenance maintenance) {
        boolean ok = maintenanceService.addMaintenance(maintenance);
        if (!ok) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(maintenance);
    }

    @PutMapping("/{maintenanceId}")
    public ResponseEntity<Void> update(@PathVariable String maintenanceId,
                                       @RequestBody Maintenance maintenance) {
        boolean ok = maintenanceService.updateMaintenance(maintenanceId, maintenance);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{maintenanceId}")
    public ResponseEntity<Void> delete(@PathVariable String maintenanceId) {
        boolean ok = maintenanceService.deleteMaintenance(maintenanceId);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Parts usage management
    @GetMapping("/{maintenanceId}/parts")
    public ResponseEntity<List<PartUsage>> getParts(@PathVariable String maintenanceId) {
        return maintenanceService.getPartsForMaintenance(maintenanceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{maintenanceId}/parts")
    public ResponseEntity<Void> addParts(@PathVariable String maintenanceId, @RequestBody List<PartUsage> parts) {
        boolean ok = maintenanceService.addPartsToMaintenance(maintenanceId, parts);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }
}
