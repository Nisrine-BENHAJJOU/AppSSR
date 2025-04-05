package ma.fstm.ilisi.projects.webmvc.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.fstm.ilisi.projects.webmvc.bo.Client;
import ma.fstm.ilisi.projects.webmvc.dto.ClientDTO;
import ma.fstm.ilisi.projects.webmvc.repository.ClientRepository;

@Service
public class ClientService implements ClientServiceInterface {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(this::fromClient).collect(Collectors.toList());
    }

    @Override
    public void saveClientDTO(ClientDTO dto) {
        clientRepository.save(this.toClient(dto));
    }

    @Override
    public void deleteClientDTO(ClientDTO dto) {
        clientRepository.delete(this.toClient(dto));
    }

    @Override
    public ClientDTO getClientDTO(int id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return this.fromClient(client.get());
        } else {
            throw new RuntimeException("Aucun client existe avec cet id: " + id);
        }
    }

    // Convert Client to ClientDTO (now public)
    public ClientDTO fromClient(Client c) {
        if (c == null) {
            System.out.println("Warning: Attempted to map a null Client object to ClientDTO");
            return null;
        }
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(c.getId());
        clientDTO.setNom(c.getNom());
        clientDTO.setCapital(c.getCapital());
        clientDTO.setCommandes(c.getCommandes());
        return clientDTO;
    }

    // Convert ClientDTO to Client (now public)
    public Client toClient(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setCapital(dto.getCapital());
        client.setCommandes(dto.getCommandes());
        return client;
    }
}