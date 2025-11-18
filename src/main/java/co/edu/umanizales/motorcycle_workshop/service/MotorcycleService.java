package co.edu.umanizales.motorcycle_workshop.service;

import co.edu.umanizales.motorcycle_workshop.model.Motorcycle;
import co.edu.umanizales.motorcycle_workshop.model.Vehicle;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleService {
    private final VehicleService vehicleService;

    public MotorcycleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public List<Motorcycle> getAll() {
        return vehicleService.getAllMotorcycles();
    }

    public Optional<Motorcycle> getById(String id) {
        return vehicleService.getVehicleById(id)
                .filter(v -> v instanceof Motorcycle)
                .map(v -> (Motorcycle) v);
    }

    public void add(Motorcycle moto) throws IOException {
        vehicleService.addVehicle(moto);
    }

    public boolean update(String id, Motorcycle updated) throws IOException {
        Optional<Vehicle> existing = vehicleService.getVehicleById(id);
        if (existing.isPresent() && existing.get() instanceof Motorcycle) {
            return vehicleService.updateVehicle(id, updated);
        }
        return false;
    }

    public boolean delete(String id) throws IOException {
        Optional<Vehicle> existing = vehicleService.getVehicleById(id);
        if (existing.isPresent() && existing.get() instanceof Motorcycle) {
            return vehicleService.deleteVehicle(id);
        }
        return false;
    }
}
