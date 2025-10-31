package co.edu.umanizales.motorcycle_workshop.service;

import co.edu.umanizales.motorcycle_workshop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing vehicles
 */
@Service
public class VehicleService {
    private List<Vehicle> vehicles = new ArrayList<>();
    private static final String VEHICLES_FILE = "vehicles";

    @Autowired
    private CSVService csvService;

    /**
     * Add a new vehicle
     */
    public void addVehicle(Vehicle vehicle) throws IOException {
        vehicles.add(vehicle);
        VehicleCSV vehicleCSV = new VehicleCSV(vehicle);
        csvService.saveToCSV(vehicleCSV, VEHICLES_FILE);
    }

    /**
     * Get all vehicles
     */
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicles);
    }

    /**
     * Get vehicle by ID
     */
    public Optional<Vehicle> getVehicleById(String id) {
        return vehicles.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst();
    }

    /**
     * Get all motorcycles
     */
    public List<Motorcycle> getAllMotorcycles() {
        List<Motorcycle> motorcycles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Motorcycle) {
                motorcycles.add((Motorcycle) vehicle);
            }
        }
        return motorcycles;
    }

    /**
     * Get all cars
     */
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Car) {
                cars.add((Car) vehicle);
            }
        }
        return cars;
    }

    /**
     * Get all trucks
     */
    public List<Truck> getAllTrucks() {
        List<Truck> trucks = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Truck) {
                trucks.add((Truck) vehicle);
            }
        }
        return trucks;
    }

    /**
     * Calculate total maintenance cost for all vehicles
     */
    public Double calculateTotalMaintenanceCost() {
        return vehicles.stream()
                .mapToDouble(Vehicle::calculateMaintenanceCost)
                .sum();
    }

    /**
     * Get vehicle with highest maintenance cost
     */
    public Optional<Vehicle> getVehicleWithHighestMaintenanceCost() {
        return vehicles.stream()
                .max((v1, v2) -> Double.compare(
                        v1.calculateMaintenanceCost(),
                        v2.calculateMaintenanceCost()));
    }

    /**
     * Delete vehicle by ID
     */
    public boolean deleteVehicle(String id) throws IOException {
        boolean removed = vehicles.removeIf(v -> v.getId().equals(id));
        if (removed) {
            // Rewrite CSV file
            List<Saveable> saveableVehicles = new ArrayList<>();
            for (Vehicle vehicle : vehicles) {
                saveableVehicles.add(new VehicleCSV(vehicle));
            }
            csvService.saveListToCSV(saveableVehicles, VEHICLES_FILE);
        }
        return removed;
    }

    /**
     * Update vehicle
     */
    public boolean updateVehicle(String id, Vehicle updatedVehicle) throws IOException {
        Optional<Vehicle> existing = getVehicleById(id);
        if (existing.isPresent()) {
            int index = vehicles.indexOf(existing.get());
            vehicles.set(index, updatedVehicle);
            // Rewrite CSV file
            List<Saveable> saveableVehicles = new ArrayList<>();
            for (Vehicle vehicle : vehicles) {
                saveableVehicles.add(new VehicleCSV(vehicle));
            }
            csvService.saveListToCSV(saveableVehicles, VEHICLES_FILE);
            return true;
        }
        return false;
    }

    /**
     * Get count of vehicles by type
     */
    public String getVehicleStatistics() {
        long motorcycleCount = vehicles.stream()
                .filter(v -> v instanceof Motorcycle)
                .count();
        long carCount = vehicles.stream()
                .filter(v -> v instanceof Car)
                .count();
        long truckCount = vehicles.stream()
                .filter(v -> v instanceof Truck)
                .count();

        return String.format("Total Vehicles: %d | Motorcycles: %d | Cars: %d | Trucks: %d",
                vehicles.size(), motorcycleCount, carCount, truckCount);
    }
}
