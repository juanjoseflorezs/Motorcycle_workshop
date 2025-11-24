package co.edu.umanizales.motorcycle_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a maintenance record for a vehicle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {
    private String maintenanceId;
    private String vehicleId;
    private String clientId;
    private String serviceId;
    private String inventoryId;
    private LocalDate maintenanceDate;
    private String description;
    private Double cost;
    private String technician;
    private String status;
    private List<PartUsage> partsUsed = new ArrayList<>();

    /**
     * Get maintenance summary
     */
    public String getSummary() {
        return String.format("Maintenance %s - Vehicle: %s - Client: %s - Service: %s - Date: %s - Cost: %.2f - Status: %s",
            maintenanceId, vehicleId, clientId, serviceId, maintenanceDate, cost, status);
    }
}
