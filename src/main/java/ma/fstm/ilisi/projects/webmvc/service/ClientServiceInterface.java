package ma.fstm.ilisi.projects.webmvc.service;

import java.util.List;
import ma.fstm.ilisi.projects.webmvc.dto.ClientDTO;

public interface ClientServiceInterface {
    List<ClientDTO> getAllClients();
    void saveClientDTO(ClientDTO dto);
    void deleteClientDTO(ClientDTO dto);
    ClientDTO getClientDTO(int id);
}