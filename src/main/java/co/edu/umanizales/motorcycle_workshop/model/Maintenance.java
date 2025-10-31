package co.edu.umanizales.motorcycle_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Class representing a maintenance record for a vehicle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {
    private String maintenanceId;
    private String vehicleId;
    private LocalDate maintenanceDate;
    private String description;
    private Double cost;
    private String technician;
    private String status;

    /**
     * Get maintenance summary
     */
    public String getSummary() {
        return String.format("Maintenance %s - Vehicle: %s - Date: %s - Cost: %.2f - Status: %s",
            maintenanceId, vehicleId, maintenanceDate, cost, status);
    }
}
