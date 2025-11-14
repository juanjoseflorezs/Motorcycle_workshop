package co.edu.umanizales.motorcycle_workshop.controller;

import co.edu.umanizales.motorcycle_workshop.model.Maintenance;
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

    @PostMapping
    public ResponseEntity<Maintenance> create(@RequestBody Maintenance maintenance) {
        maintenanceService.addMaintenance(maintenance);
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
}
