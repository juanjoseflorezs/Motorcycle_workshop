package co.edu.umanizales.motorcycle_workshop.controller;

import co.edu.umanizales.motorcycle_workshop.model.*;
import co.edu.umanizales.motorcycle_workshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prog3")
public class Prog3Controller {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private SparePartService sparePartService;

    @Autowired
    private InventoryService inventoryService;

    /**
     * Welcome endpoint
     */
    @GetMapping
    public String getHello() {
        return "Hola campeones - Motorcycle Workshop API";
    }

    /**
     * Add a new motorcycle
     */
    @PostMapping("/motorcycle")
    public ResponseEntity<String> addMotorcycle(@RequestBody Motorcycle motorcycle) {
        try {
            vehicleService.addVehicle(motorcycle);
            return ResponseEntity.ok("Motorcycle added successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error saving motorcycle: " + e.getMessage());
        }
    }

    /**
     * Add a new car
     */
    @PostMapping("/car")
    public ResponseEntity<String> addCar(@RequestBody Car car) {
        try {
            vehicleService.addVehicle(car);
            return ResponseEntity.ok("Car added successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error saving car: " + e.getMessage());
        }
    }

    /**
     * Add a new truck
     */
    @PostMapping("/truck")
    public ResponseEntity<String> addTruck(@RequestBody Truck truck) {
        try {
            vehicleService.addVehicle(truck);
            return ResponseEntity.ok("Truck added successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error saving truck: " + e.getMessage());
        }
    }

    /**
     * Get all vehicles
     */
    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    /**
     * Get vehicle by ID
     */
    @GetMapping("/vehicle/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable String id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle.isPresent()) {
            return ResponseEntity.ok(vehicle.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get all motorcycles
     */
    @GetMapping("/motorcycles")
    public ResponseEntity<List<Motorcycle>> getAllMotorcycles() {
        return ResponseEntity.ok(vehicleService.getAllMotorcycles());
    }

    /**
     * Get all cars
     */
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(vehicleService.getAllCars());
    }

    /**
     * Get all trucks
     */
    @GetMapping("/trucks")
    public ResponseEntity<List<Truck>> getAllTrucks() {
        return ResponseEntity.ok(vehicleService.getAllTrucks());
    }

    /**
     * Calculate maintenance cost for a vehicle
     */
    @GetMapping("/maintenance-cost/{id}")
    public ResponseEntity<?> getMaintenanceCost(@PathVariable String id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle.isPresent()) {
            Double cost = vehicle.get().calculateMaintenanceCost();
            return ResponseEntity.ok("Maintenance Cost: " + cost);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get total maintenance cost for all vehicles
     */
    @GetMapping("/total-maintenance-cost")
    public ResponseEntity<String> getTotalMaintenanceCost() {
        Double totalCost = vehicleService.calculateTotalMaintenanceCost();
        return ResponseEntity.ok("Total Maintenance Cost: " + totalCost);
    }

    /**
     * Get vehicle with highest maintenance cost
     */
    @GetMapping("/highest-maintenance")
    public ResponseEntity<?> getVehicleWithHighestMaintenanceCost() {
        Optional<Vehicle> vehicle = vehicleService.getVehicleWithHighestMaintenanceCost();
        if (vehicle.isPresent()) {
            return ResponseEntity.ok(vehicle.get().getFullDescription() + 
                    " - Cost: " + vehicle.get().calculateMaintenanceCost());
        }
        return ResponseEntity.ok("No vehicles found");
    }

    /**
     * Delete vehicle by ID
     */
    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String id) {
        try {
            boolean deleted = vehicleService.deleteVehicle(id);
            if (deleted) {
                return ResponseEntity.ok("Vehicle deleted successfully");
            }
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error deleting vehicle: " + e.getMessage());
        }
    }

    /**
     * Get vehicle statistics
     */
    @GetMapping("/statistics")
    public ResponseEntity<String> getStatistics() {
        return ResponseEntity.ok(vehicleService.getVehicleStatistics());
    }

    // ==================== CLIENT ENDPOINTS ====================

    /**
     * Add a new client
     */
    @PostMapping("/client")
    public ResponseEntity<String> addClient(@RequestBody Client client) {
        clientService.addClient(client);
        return ResponseEntity.ok("Client added successfully");
    }

    /**
     * Get all clients
     */
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    /**
     * Get client by ID
     */
    @GetMapping("/client/{id}")
    public ResponseEntity<?> getClientById(@PathVariable String id) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Search clients by name
     */
    @GetMapping("/clients/search/{name}")
    public ResponseEntity<List<Client>> searchClientsByName(@PathVariable String name) {
        return ResponseEntity.ok(clientService.searchClientsByName(name));
    }

    /**
     * Get clients by city
     */
    @GetMapping("/clients/city/{city}")
    public ResponseEntity<List<Client>> getClientsByCity(@PathVariable String city) {
        return ResponseEntity.ok(clientService.getClientsByCity(city));
    }

    /**
     * Delete client
     */
    @DeleteMapping("/client/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable String id) {
        if (clientService.deleteClient(id)) {
            return ResponseEntity.ok("Client deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    // ==================== SERVICE ENDPOINTS ====================

    /**
     * Add a new service
     */
    @PostMapping("/service")
    public ResponseEntity<String> addService(@RequestBody Service service) {
        serviceService.addService(service);
        return ResponseEntity.ok("Service added successfully");
    }

    /**
     * Get all services
     */
    @GetMapping("/services")
    public ResponseEntity<List<Service>> getAllServices() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }

    /**
     * Get active services
     */
    @GetMapping("/services/active")
    public ResponseEntity<List<Service>> getActiveServices() {
        return ResponseEntity.ok(serviceService.getActiveServices());
    }

    /**
     * Get service by ID
     */
    @GetMapping("/service/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable String id) {
        Optional<Service> service = serviceService.getServiceById(id);
        if (service.isPresent()) {
            return ResponseEntity.ok(service.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get services by category
     */
    @GetMapping("/services/category/{category}")
    public ResponseEntity<List<Service>> getServicesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(serviceService.getServicesByCategory(category));
    }

    /**
     * Delete service
     */
    @DeleteMapping("/service/{id}")
    public ResponseEntity<String> deleteService(@PathVariable String id) {
        if (serviceService.deleteService(id)) {
            return ResponseEntity.ok("Service deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    // ==================== SPARE PART ENDPOINTS ====================

    /**
     * Add a new spare part
     */
    @PostMapping("/spare-part")
    public ResponseEntity<String> addSparePart(@RequestBody SparePart part) {
        sparePartService.addSparePart(part);
        return ResponseEntity.ok("Spare part added successfully");
    }

    /**
     * Get all spare parts
     */
    @GetMapping("/spare-parts")
    public ResponseEntity<List<SparePart>> getAllSpareParts() {
        return ResponseEntity.ok(sparePartService.getAllSpareParts());
    }

    /**
     * Get available spare parts
     */
    @GetMapping("/spare-parts/available")
    public ResponseEntity<List<SparePart>> getAvailableSpareParts() {
        return ResponseEntity.ok(sparePartService.getAvailableParts());
    }

    /**
     * Get low stock spare parts
     */
    @GetMapping("/spare-parts/low-stock")
    public ResponseEntity<List<SparePart>> getLowStockSpareParts() {
        return ResponseEntity.ok(sparePartService.getLowStockParts());
    }

    /**
     * Get spare part by ID
     */
    @GetMapping("/spare-part/{id}")
    public ResponseEntity<?> getSparePartById(@PathVariable String id) {
        Optional<SparePart> part = sparePartService.getSparePartById(id);
        if (part.isPresent()) {
            return ResponseEntity.ok(part.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get spare parts by vehicle type
     */
    @GetMapping("/spare-parts/vehicle/{vehicleType}")
    public ResponseEntity<List<SparePart>> getSparePartsByVehicleType(@PathVariable String vehicleType) {
        return ResponseEntity.ok(sparePartService.getPartsByVehicleType(vehicleType));
    }

    /**
     * Get spare parts by category
     */
    @GetMapping("/spare-parts/category/{category}")
    public ResponseEntity<List<SparePart>> getSparePartsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(sparePartService.getPartsByCategory(category));
    }

    /**
     * Update spare part stock
     */
    @PutMapping("/spare-part/{id}/stock/{quantity}")
    public ResponseEntity<String> updateSparePartStock(@PathVariable String id, @PathVariable Integer quantity) {
        if (sparePartService.updatePartStock(id, quantity)) {
            return ResponseEntity.ok("Stock updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Delete spare part
     */
    @DeleteMapping("/spare-part/{id}")
    public ResponseEntity<String> deleteSparePart(@PathVariable String id) {
        if (sparePartService.deleteSparePart(id)) {
            return ResponseEntity.ok("Spare part deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get total inventory value
     */
    @GetMapping("/spare-parts/inventory-value")
    public ResponseEntity<String> getTotalInventoryValue() {
        Double value = sparePartService.getTotalInventoryValue();
        return ResponseEntity.ok("Total Inventory Value: " + value);
    }

    // ==================== INVENTORY ENDPOINTS ====================

    /**
     * Create inventory
     */
    @PostMapping("/inventory")
    public ResponseEntity<String> createInventory(@RequestBody Inventory inventory) {
        inventoryService.createInventory(inventory);
        return ResponseEntity.ok("Inventory created successfully");
    }

    /**
     * Get all inventories
     */
    @GetMapping("/inventories")
    public ResponseEntity<List<Inventory>> getAllInventories() {
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    /**
     * Get inventory by ID
     */
    @GetMapping("/inventory/{id}")
    public ResponseEntity<?> getInventoryById(@PathVariable String id) {
        Optional<Inventory> inventory = inventoryService.getInventoryById(id);
        if (inventory.isPresent()) {
            return ResponseEntity.ok(inventory.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get low stock items from inventory
     */
    @GetMapping("/inventory/{id}/low-stock")
    public ResponseEntity<List<SparePart>> getLowStockFromInventory(@PathVariable String id) {
        return ResponseEntity.ok(inventoryService.getLowStockItems(id));
    }

    /**
     * Get inventory total value
     */
    @GetMapping("/inventory/{id}/total-value")
    public ResponseEntity<String> getInventoryTotalValue(@PathVariable String id) {
        Double value = inventoryService.getTotalInventoryValue(id);
        return ResponseEntity.ok("Total Inventory Value: " + value);
    }

    /**
     * Get inventory summary
     */
    @GetMapping("/inventory/{id}/summary")
    public ResponseEntity<String> getInventorySummary(@PathVariable String id) {
        return ResponseEntity.ok(inventoryService.getInventorySummary(id));
    }

    /**
     * Generate inventory report
     */
    @GetMapping("/inventory/{id}/report")
    public ResponseEntity<String> generateInventoryReport(@PathVariable String id) {
        return ResponseEntity.ok(inventoryService.generateInventoryReport(id));
    }

    /**
     * Delete inventory
     */
    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable String id) {
        if (inventoryService.deleteInventory(id)) {
            return ResponseEntity.ok("Inventory deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
