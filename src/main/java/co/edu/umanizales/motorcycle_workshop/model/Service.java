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
    private Double baseCost;
    private Double estimatedHours;
    private String category;
    private Boolean isActive;

    /**
     * Calculate service cost with labor
     * Labor cost = baseCost + (estimatedHours * hourlyRate)
     */
    public Double calculateCost(Double hourlyRate) {
        return baseCost + (estimatedHours * hourlyRate);
    }

    /**
     * Get service details
     */
    public String getServiceDetails() {
        return String.format("Service: %s (%s) - Description: %s - Base Cost: %.2f - Hours: %.1f",
            serviceName, serviceId, description, baseCost, estimatedHours);
    }

    /**
     * Get service information for display
     */
    public String getServiceInfo() {
        String status = isActive ? "Active" : "Inactive";
        return String.format("%s - Category: %s - Status: %s - Base Cost: %.2f",
            serviceName, category, status, baseCost);
    }

    /**
     * Check if service is available
     */
    public Boolean isAvailable() {
        return isActive;
    }
}
