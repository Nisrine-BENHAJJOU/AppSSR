package ma.fstm.ilisi.projects.webmvc.service;

import ma.fstm.ilisi.projects.webmvc.bo.Commande;
import ma.fstm.ilisi.projects.webmvc.dto.CommandeDTO;
import ma.fstm.ilisi.projects.webmvc.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ClientService clientService;

    public List<CommandeDTO> getAllCommandes() {
        List<Commande> commandes = commandeRepository.findAll();
        System.out.println("Retrieved Commandes from database: " + commandes);
        return commandes.stream()
                .filter(commande -> commande != null)
                .map(this::fromCommande)
                .filter(commandeDTO -> commandeDTO != null)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveCommandeDTO(CommandeDTO dto) {
        commandeRepository.save(this.toCommande(dto));
    }

    @Transactional
    public void deleteCommandeDTO(CommandeDTO dto) {
        commandeRepository.delete(this.toCommande(dto));
    }

    public CommandeDTO getCommandeDTO(int id) {
        Optional<Commande> commande = commandeRepository.findById(id);
        if (commande.isPresent()) {
            return this.fromCommande(commande.get());
        } else {
            throw new RuntimeException("Aucune commande existe avec cet id: " + id);
        }
    }

    private CommandeDTO fromCommande(Commande c) {
        if (c == null) {
            System.out.println("Warning: Attempted to map a null Commande object to CommandeDTO");
            return null; // Or throw an exception, depending on your requirements
        }
        CommandeDTO commandeDTO = new CommandeDTO();
        commandeDTO.setId(c.getId());
        commandeDTO.setDate(c.getDate());
        commandeDTO.setClient(clientService.fromClient(c.getClient()));
        return commandeDTO;
    }

    private Commande toCommande(CommandeDTO dto) {
        Commande commande = new Commande();
        commande.setId(dto.getId());
        commande.setDate(dto.getDate());
        commande.setClient(clientService.toClient(dto.getClient()));
        return commande;
    }
}