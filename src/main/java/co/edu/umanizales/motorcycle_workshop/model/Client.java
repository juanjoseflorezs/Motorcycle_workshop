package co.edu.umanizales.motorcycle_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing a Client of the motorcycle workshop
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private String clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String documentType;
    private String documentNumber;
    private String registrationDate;

    /**
     * Get full name of the client
     */
    public String getFullName() {
        return (firstName + " " + lastName).trim();
    }

    /**
     * Get contact information
     */
    public String getClientInfo() {
        return String.format("""
            Client Information:
            ID: %s
            Name: %s
            Document: %s %s
            Contact: %s | %s
            Address: %s, %s
            """,
            clientId, getFullName(), documentType, documentNumber,
            email, phone, address, city);
    }

    /**
     * Update client profile
     */
    public void updateProfile(String email, String phone, String address, String city) {
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
    }
    /**
     * Get client summary
     */
    public String getSummary() {
        return String.format("Client: %s (%s) - Document: %s %s - Phone: %s",
            getFullName(), clientId, documentType, documentNumber, phone);
    }
}
