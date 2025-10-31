package co.edu.umanizales.motorcycle_workshop.model;

/**
 * Interface for objects that can be saved to CSV
 */
public interface Saveable {
    /**
     * Convert object to CSV format
     * @return CSV formatted string
     */
    String toCSV();

    /**
     * Get CSV header for this object type
     * @return CSV header string
     */
    String getCSVHeader();
}
