package co.edu.umanizales.motorcycle_workshop.service;

import co.edu.umanizales.motorcycle_workshop.model.Maintenance;
import co.edu.umanizales.motorcycle_workshop.model.PartUsage;
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
    private final VehicleService vehicleService;
    private final ClientService clientService;
    private final ServiceService serviceService;
    private final InventoryService inventoryService;

    public MaintenanceService(VehicleService vehicleService,
                              ClientService clientService,
                              ServiceService serviceService,
                              InventoryService inventoryService) {
        this.vehicleService = vehicleService;
        this.clientService = clientService;
        this.serviceService = serviceService;
        this.inventoryService = inventoryService;
    }

    public boolean addMaintenance(Maintenance maintenance) {
        // Validate vehicle exists
        if (maintenance.getVehicleId() == null ||
                vehicleService.getVehicleById(maintenance.getVehicleId()).isEmpty()) {
            return false;
        }
        // Validate client exists and owns the vehicle (if clientId provided)
        if (maintenance.getClientId() != null) {
            boolean clientExists = clientService.getClientById(maintenance.getClientId()).isPresent();
            if (!clientExists) { return false; }
            // Check ownership
            var vehicleOpt = vehicleService.getVehicleById(maintenance.getVehicleId());
            if (vehicleOpt.isPresent()) {
                String owner = vehicleOpt.get().getOwnerClientId();
                if (owner != null && !owner.equals(maintenance.getClientId())) {
                    return false;
                }
            }
        }
        // Validate service exists (if provided)
        if (maintenance.getServiceId() != null &&
                serviceService.getServiceById(maintenance.getServiceId()).isEmpty()) {
            return false;
        }
        maintenances.add(maintenance);
        return true;
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

    public List<Maintenance> getByClientId(String clientId) {
        return maintenances.stream()
                .filter(m -> clientId.equals(m.getClientId()))
                .collect(Collectors.toList());
    }

    public List<Maintenance> getByServiceId(String serviceId) {
        return maintenances.stream()
                .filter(m -> serviceId.equals(m.getServiceId()))
                .collect(Collectors.toList());
    }

    public boolean updateMaintenance(String maintenanceId, Maintenance updated) {
        Optional<Maintenance> existing = getById(maintenanceId);
        if (existing.isPresent()) {
            // Validate vehicle exists
            if (updated.getVehicleId() == null ||
                    vehicleService.getVehicleById(updated.getVehicleId()).isEmpty()) {
                return false;
            }
            // Validate client exists and owns the vehicle (if clientId provided)
            if (updated.getClientId() != null) {
                boolean clientExists = clientService.getClientById(updated.getClientId()).isPresent();
                if (!clientExists) { return false; }
                var vehicleOpt = vehicleService.getVehicleById(updated.getVehicleId());
                if (vehicleOpt.isPresent()) {
                    String owner = vehicleOpt.get().getOwnerClientId();
                    if (owner != null && !owner.equals(updated.getClientId())) {
                        return false;
                    }
                }
            }
            // Validate service exists (if provided)
            if (updated.getServiceId() != null &&
                    serviceService.getServiceById(updated.getServiceId()).isEmpty()) {
                return false;
            }
            int idx = maintenances.indexOf(existing.get());
            maintenances.set(idx, updated);
            return true;
        }
        return false;
    }

    public boolean deleteMaintenance(String maintenanceId) {
        return maintenances.removeIf(m -> m.getMaintenanceId().equals(maintenanceId));
    }

    public Optional<List<PartUsage>> getPartsForMaintenance(String maintenanceId) {
        return getById(maintenanceId).map(Maintenance::getPartsUsed);
    }

    public boolean addPartsToMaintenance(String maintenanceId, List<PartUsage> parts) {
        Optional<Maintenance> opt = getById(maintenanceId);
        if (opt.isEmpty()) { return false; }
        Maintenance m = opt.get();
        // Validate inventory reference
        if (m.getInventoryId() == null ||
                inventoryService.getInventoryById(m.getInventoryId()).isEmpty()) {
            return false;
        }
        // Validate each part exists and decrement stock
        for (PartUsage pu : parts) {
            if (pu.getPartId() == null || pu.getQuantity() <= 0) {
                return false;
            }
            // Check part exists in inventory
            var partOpt = inventoryService.getPartFromInventory(m.getInventoryId(), pu.getPartId());
            if (partOpt.isEmpty()) { return false; }
            // If unitPrice not provided, take from catalog
            if (pu.getUnitPrice() == null) {
                pu.setUnitPrice(partOpt.get().getUnitPrice());
            }
        }
        // All good, apply stock updates and append to maintenance
        for (PartUsage pu : parts) {
            boolean stockOk = inventoryService.updatePartStock(m.getInventoryId(), pu.getPartId(), -pu.getQuantity());
            if (!stockOk) { return false; }
            m.getPartsUsed().add(pu);
        }
        return true;
    }
}
