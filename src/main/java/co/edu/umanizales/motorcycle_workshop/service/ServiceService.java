package co.edu.umanizales.motorcycle_workshop.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing workshop services
 */
@Service
public class ServiceService {
    private List<co.edu.umanizales.motorcycle_workshop.model.Service> services = new ArrayList<>();

    /**
     * Add a new service
     */
    public void addService(co.edu.umanizales.motorcycle_workshop.model.Service service) {
        services.add(service);
    }

    /**
     * Get all services
     */
    public List<co.edu.umanizales.motorcycle_workshop.model.Service> getAllServices() {
        return new ArrayList<>(services);
    }

    /**
     * Get all active services
     */
    public List<co.edu.umanizales.motorcycle_workshop.model.Service> getActiveServices() {
        List<co.edu.umanizales.motorcycle_workshop.model.Service> activeServices = new ArrayList<>();
        for (co.edu.umanizales.motorcycle_workshop.model.Service service : services) {
            if (service.isAvailable()) {
                activeServices.add(service);
            }
        }
        return activeServices;
    }

    /**
     * Get service by ID
     */
    public Optional<co.edu.umanizales.motorcycle_workshop.model.Service> getServiceById(String serviceId) {
        return services.stream()
                .filter(s -> s.getServiceId().equals(serviceId))
                .findFirst();
    }

    /**
     * Get services by category
     */
    public List<co.edu.umanizales.motorcycle_workshop.model.Service> getServicesByCategory(String category) {
        List<co.edu.umanizales.motorcycle_workshop.model.Service> result = new ArrayList<>();
        for (co.edu.umanizales.motorcycle_workshop.model.Service service : services) {
            if (service.getCategory().equalsIgnoreCase(category)) {
                result.add(service);
            }
        }
        return result;
    }

    /**
     * Update service
     */
    public boolean updateService(String serviceId, co.edu.umanizales.motorcycle_workshop.model.Service updatedService) {
        Optional<co.edu.umanizales.motorcycle_workshop.model.Service> existing = getServiceById(serviceId);
        if (existing.isPresent()) {
            int index = services.indexOf(existing.get());
            services.set(index, updatedService);
            return true;
        }
        return false;
    }

    /**
     * Delete service
     */
    public boolean deleteService(String serviceId) {
        return services.removeIf(s -> s.getServiceId().equals(serviceId));
    }

    /**
     * Activate service
     */
    public boolean activateService(String serviceId) {
        Optional<co.edu.umanizales.motorcycle_workshop.model.Service> service = getServiceById(serviceId);
        if (service.isPresent()) {
            service.get().setIsActive(true);
            return true;
        }
        return false;
    }

    /**
     * Deactivate service
     */
    public boolean deactivateService(String serviceId) {
        Optional<co.edu.umanizales.motorcycle_workshop.model.Service> service = getServiceById(serviceId);
        if (service.isPresent()) {
            service.get().setIsActive(false);
            return true;
        }
        return false;
    }

    /**
     * Get average service cost
     */
    public Double getAverageServiceCost() {
        if (services.isEmpty()) {
            return 0.0;
        }
        return services.stream()
                .mapToDouble(co.edu.umanizales.motorcycle_workshop.model.Service::getBaseCost)
                .average()
                .orElse(0.0);
    }

    /**
     * Get total number of services
     */
    public Integer getTotalServices() {
        return services.size();
    }

    /**
     * Search services by name
     */
    public List<co.edu.umanizales.motorcycle_workshop.model.Service> searchServicesByName(String name) {
        List<co.edu.umanizales.motorcycle_workshop.model.Service> result = new ArrayList<>();
        String searchTerm = name.toLowerCase();
        for (co.edu.umanizales.motorcycle_workshop.model.Service service : services) {
            if (service.getServiceName().toLowerCase().contains(searchTerm)) {
                result.add(service);
            }
        }
        return result;
    }
}
