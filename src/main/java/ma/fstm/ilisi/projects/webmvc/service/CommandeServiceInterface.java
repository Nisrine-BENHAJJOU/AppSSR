package ma.fstm.ilisi.projects.webmvc.service;
import org.springframework.stereotype.Service;

import ma.fstm.ilisi.projects.webmvc.dto.CommandeDTO;

import java.util.List;

public interface CommandeServiceInterface {

    public List<CommandeDTO> getAllCommandes();

    public void saveCommandeDTO(CommandeDTO dto);

    public void deleteCommandeDTO(CommandeDTO dto);

    public CommandeDTO getCommandeDTO(int id);


}