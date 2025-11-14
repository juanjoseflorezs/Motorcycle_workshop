package co.edu.umanizales.motorcycle_workshop.service;

import co.edu.umanizales.motorcycle_workshop.model.Maintenance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for managing maintenance records
 */
@Service
public class MaintenanceService {
    private final List<Maintenance> maintenances = new ArrayList<>();

    public void addMaintenance(Maintenance maintenance) {
        maintenances.add(maintenance);
    }

    public List<Maintenance> getAllMaintenances() {
        return new ArrayList<>(maintenances);
    }

    public Optional<Maintenance> getById(String maintenanceId) {
        return maintenances.stream()
                .filter(m -> m.getMaintenanceId().equals(maintenanceId))
                .findFirst();
    }

    public List<Maintenance> getByVehicleId(String vehicleId) {
        return maintenances.stream()
                .filter(m -> m.getVehicleId().equals(vehicleId))
                .collect(Collectors.toList());
    }

    public boolean updateMaintenance(String maintenanceId, Maintenance updated) {
        Optional<Maintenance> existing = getById(maintenanceId);
        if (existing.isPresent()) {
            int idx = maintenances.indexOf(existing.get());
            maintenances.set(idx, updated);
            return true;
        }
        return false;
    }

    public boolean deleteMaintenance(String maintenanceId) {
        return maintenances.removeIf(m -> m.getMaintenanceId().equals(maintenanceId));
    }
}
