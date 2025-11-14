package co.edu.umanizales.motorcycle_workshop.controller;

import co.edu.umanizales.motorcycle_workshop.model.*;
import co.edu.umanizales.motorcycle_workshop.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // List & stats
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAll() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/stats/maintenance/total")
    public ResponseEntity<Double> totalMaintenanceCost() {
        return ResponseEntity.ok(vehicleService.calculateTotalMaintenanceCost());
    }

    @GetMapping("/stats/type")
    public ResponseEntity<String> typeStats() {
        return ResponseEntity.ok(vehicleService.getVehicleStatistics());
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable String id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create
    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) throws IOException {
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok(vehicle);
    }

    // Specialized creators to avoid polymorphic issues when posting JSON
    @PostMapping("/motorcycles")
    public ResponseEntity<Motorcycle> addMotorcycle(@RequestBody Motorcycle moto) throws IOException {
        vehicleService.addVehicle(moto);
        return ResponseEntity.ok(moto);
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> addCar(@RequestBody Car car) throws IOException {
        vehicleService.addVehicle(car);
        return ResponseEntity.ok(car);
    }

    @PostMapping("/trucks")
    public ResponseEntity<Truck> addTruck(@RequestBody Truck truck) throws IOException {
        vehicleService.addVehicle(truck);
        return ResponseEntity.ok(truck);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody Vehicle vehicle) throws IOException {
        boolean ok = vehicleService.updateVehicle(id, vehicle);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) throws IOException {
        boolean ok = vehicleService.deleteVehicle(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // By type
    @GetMapping("/motorcycles")
    public ResponseEntity<List<Motorcycle>> allMotorcycles() {
        return ResponseEntity.ok(vehicleService.getAllMotorcycles());
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> allCars() {
        return ResponseEntity.ok(vehicleService.getAllCars());
    }

    @GetMapping("/trucks")
    public ResponseEntity<List<Truck>> allTrucks() {
        return ResponseEntity.ok(vehicleService.getAllTrucks());
    }

    @GetMapping("/maintenance/highest")
    public ResponseEntity<Vehicle> highestMaintenance() {
        return vehicleService.getVehicleWithHighestMaintenanceCost()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
