package co.edu.umanizales.motorcycle_workshop.service;

import co.edu.umanizales.motorcycle_workshop.model.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing clients
 */
@Service
public class ClientService {
    private List<Client> clients = new ArrayList<>();

    /**
     * Add a new client
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    /**
     * Get all clients
     */
    public List<Client> getAllClients() {
        return new ArrayList<>(clients);
    }

    /**
     * Get client by ID
     */
    public Optional<Client> getClientById(String clientId) {
        return clients.stream()
                .filter(c -> c.getClientId().equals(clientId))
                .findFirst();
    }

    /**
     * Get client by email
     */
    public Optional<Client> getClientByEmail(String email) {
        return clients.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    /**
     * Get clients by city
     */
    public List<Client> getClientsByCity(String city) {
        List<Client> result = new ArrayList<>();
        for (Client client : clients) {
            if (client.getCity().equalsIgnoreCase(city)) {
                result.add(client);
            }
        }
        return result;
    }

    /**
     * Update client information
     */
    public boolean updateClient(String clientId, Client updatedClient) {
        Optional<Client> existing = getClientById(clientId);
        if (existing.isPresent()) {
            int index = clients.indexOf(existing.get());
            clients.set(index, updatedClient);
            return true;
        }
        return false;
    }

    /**
     * Delete client
     */
    public boolean deleteClient(String clientId) {
        return clients.removeIf(c -> c.getClientId().equals(clientId));
    }

    /**
     * Get total number of clients
     */
    public Integer getTotalClients() {
        return clients.size();
    }

    /**
     * Search clients by name
     */
    public List<Client> searchClientsByName(String name) {
        List<Client> result = new ArrayList<>();
        String searchTerm = name.toLowerCase();
        for (Client client : clients) {
            if (client.getFullName().toLowerCase().contains(searchTerm)) {
                result.add(client);
            }
        }
        return result;
    }
}
