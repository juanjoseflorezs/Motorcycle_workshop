package co.edu.umanizales.motorcycle_workshop.service;

import co.edu.umanizales.motorcycle_workshop.model.Car;
import co.edu.umanizales.motorcycle_workshop.model.Vehicle;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final VehicleService vehicleService;

    public CarService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public List<Car> getAll() {
        return vehicleService.getAllCars();
    }

    public Optional<Car> getById(String id) {
        return vehicleService.getVehicleById(id)
                .filter(v -> v instanceof Car)
                .map(v -> (Car) v);
    }

    public void add(Car car) throws IOException {
        vehicleService.addVehicle(car);
    }

    public boolean update(String id, Car updated) throws IOException {
        Optional<Vehicle> existing = vehicleService.getVehicleById(id);
        if (existing.isPresent() && existing.get() instanceof Car) {
            return vehicleService.updateVehicle(id, updated);
        }
        return false;
    }

    public boolean delete(String id) throws IOException {
        Optional<Vehicle> existing = vehicleService.getVehicleById(id);
        if (existing.isPresent() && existing.get() instanceof Car) {
            return vehicleService.deleteVehicle(id);
        }
        return false;
    }
}
