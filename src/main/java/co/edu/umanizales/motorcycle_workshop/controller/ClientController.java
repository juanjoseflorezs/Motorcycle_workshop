package co.edu.umanizales.motorcycle_workshop.controller;

import co.edu.umanizales.motorcycle_workshop.model.Client;
import co.edu.umanizales.motorcycle_workshop.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getById(@PathVariable String clientId) {
        return clientService.getClientById(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        clientService.addClient(client);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Void> update(@PathVariable String clientId, @RequestBody Client client) {
        boolean ok = clientService.updateClient(clientId, client);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable String clientId) {
        boolean ok = clientService.deleteClient(clientId);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-email")
    public ResponseEntity<Client> getByEmail(@RequestParam String email) {
        return clientService.getClientByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-city")
    public ResponseEntity<List<Client>> getByCity(@RequestParam String city) {
        return ResponseEntity.ok(clientService.getClientsByCity(city));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Client>> searchByName(@RequestParam String q) {
        return ResponseEntity.ok(clientService.searchClientsByName(q));
    }

    @GetMapping("/stats/total")
    public ResponseEntity<Integer> total() {
        return ResponseEntity.ok(clientService.getTotalClients());
    }
}
