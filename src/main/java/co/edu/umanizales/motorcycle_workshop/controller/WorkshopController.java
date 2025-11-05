package co.edu.umanizales.motorcycle_workshop.controller;

import co.edu.umanizales.motorcycle_workshop.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workshop")
public class WorkshopController {

    // In-memory storage (in a real app, replace with a service layer and database)
    private List<Client> clients = new ArrayList<>();
    private List<Motorcycle> motorcycles = new ArrayList<>();
    private List<Service> services = new ArrayList<>();
    private Inventory inventory = new Inventory("INV-001", "Main Warehouse");

    // Client Endpoints
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable String clientId) {
        return clients.stream()
                .filter(c -> c.getClientId().equals(clientId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        clients.add(client);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/clients/{clientId}")
    public ResponseEntity<Client> updateClient(
            @PathVariable String clientId, 
            @RequestBody Client updatedClient) {
        
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getClientId().equals(clientId)) {
                clients.set(i, updatedClient);
                return ResponseEntity.ok(updatedClient);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // Motorcycle Endpoints
    @GetMapping("/motorcycles")
    public ResponseEntity<List<Motorcycle>> getAllMotorcycles() {
        return ResponseEntity.ok(motorcycles);
    }

    @PostMapping("/motorcycles")
    public ResponseEntity<Motorcycle> addMotorcycle(@RequestBody Motorcycle motorcycle) {
        motorcycles.add(motorcycle);
        return ResponseEntity.ok(motorcycle);
    }

    // Service Endpoints
    @GetMapping("/services")
    public ResponseEntity<List<Service>> getAllServices() {
        return ResponseEntity.ok(services);
    }

    @PostMapping("/services")
    public ResponseEntity<Service> createService(@RequestBody Service service) {
        services.add(service);
        return ResponseEntity.ok(service);
    }

    // Inventory Endpoints
    @GetMapping("/inventory")
    public ResponseEntity<Inventory> getInventory() {
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/inventory/parts")
    public ResponseEntity<SparePart> addSparePart(@RequestBody SparePart part) {
        inventory.addPart(part);
        return ResponseEntity.ok(part);
    }

    @GetMapping("/inventory/parts/low-stock")
    public ResponseEntity<List<SparePart>> getLowStockParts() {
        List<SparePart> lowStock = inventory.getSpareParts().stream()
                .filter(SparePart::isLowStock)
                .toList();
        return ResponseEntity.ok(lowStock);
    }
}
