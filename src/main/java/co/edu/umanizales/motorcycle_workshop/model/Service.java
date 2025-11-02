package co.edu.umanizales.motorcycle_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing a Service offered by the workshop
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    private String serviceId;
    private String serviceName;
    private String description;
    private double baseCost;
    private double estimatedHours;
    private String category;
    private boolean isActive;

    /**
     * Calculate service cost with labor
     * Labor cost = baseCost + (estimatedHours * hourlyRate)
     */
    public double calculateCost(double hourlyRate) {
        return baseCost + (estimatedHours * hourlyRate);
    }

    /**
     * Get service details
     */
    public String getServiceDetails() {
        return String.format("Service: %s (%s) - Base: $%,.2f - Hours: %.1f - %s",
            serviceName, serviceId, baseCost, estimatedHours, description);
    }

    /**
     * Get service information for display
     */
    public String getServiceInfo() {
        return String.format("%s - %s [%s] - $%,.2f",
            serviceName, category, isActive ? "Active" : "Inactive", baseCost);
    }

    /**
     * Check if service is available
     */
    public boolean isAvailable() {
        return isActive;
    }
}
