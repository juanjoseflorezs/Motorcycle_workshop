package co.edu.umanizales.motorcycle_workshop.controller;

import co.edu.umanizales.motorcycle_workshop.model.Service;
import co.edu.umanizales.motorcycle_workshop.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<List<Service>> getAll() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Service>> getActive() {
        return ResponseEntity.ok(serviceService.getActiveServices());
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<Service> getById(@PathVariable String serviceId) {
        return serviceService.getServiceById(serviceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Service> create(@RequestBody Service srv) {
        serviceService.addService(srv);
        return ResponseEntity.ok(srv);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<Void> update(@PathVariable String serviceId, @RequestBody Service srv) {
        boolean ok = serviceService.updateService(serviceId, srv);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> delete(@PathVariable String serviceId) {
        boolean ok = serviceService.deleteService(serviceId);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<Service>> byCategory(@RequestParam String category) {
        return ResponseEntity.ok(serviceService.getServicesByCategory(category));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Service>> search(@RequestParam String q) {
        return ResponseEntity.ok(serviceService.searchServicesByName(q));
    }

    @PostMapping("/{serviceId}/activate")
    public ResponseEntity<Void> activate(@PathVariable String serviceId) {
        boolean ok = serviceService.activateService(serviceId);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/{serviceId}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable String serviceId) {
        boolean ok = serviceService.deactivateService(serviceId);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/stats/average-cost")
    public ResponseEntity<Double> avgCost() {
        return ResponseEntity.ok(serviceService.getAverageServiceCost());
    }

    @GetMapping("/stats/total")
    public ResponseEntity<Integer> total() {
        return ResponseEntity.ok(serviceService.getTotalServices());
    }
}
