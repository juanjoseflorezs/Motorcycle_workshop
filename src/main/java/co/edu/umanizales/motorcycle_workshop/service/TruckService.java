package co.edu.umanizales.motorcycle_workshop.service;

import co.edu.umanizales.motorcycle_workshop.model.Truck;
import co.edu.umanizales.motorcycle_workshop.model.Vehicle;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TruckService {
    private final VehicleService vehicleService;

    public TruckService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public List<Truck> getAll() {
        return vehicleService.getAllTrucks();
    }

    public Optional<Truck> getById(String id) {
        return vehicleService.getVehicleById(id)
                .filter(v -> v instanceof Truck)
                .map(v -> (Truck) v);
    }

    public void add(Truck truck) throws IOException {
        vehicleService.addVehicle(truck);
    }

    public boolean update(String id, Truck updated) throws IOException {
        Optional<Vehicle> existing = vehicleService.getVehicleById(id);
        if (existing.isPresent() && existing.get() instanceof Truck) {
            return vehicleService.updateVehicle(id, updated);
        }
        return false;
    }

    public boolean delete(String id) throws IOException {
        Optional<Vehicle> existing = vehicleService.getVehicleById(id);
        if (existing.isPresent() && existing.get() instanceof Truck) {
            return vehicleService.deleteVehicle(id);
        }
        return false;
    }
}
