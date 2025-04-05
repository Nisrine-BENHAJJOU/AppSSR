package ma.fstm.ilisi.projects.webmvc.controller;

import ma.fstm.ilisi.projects.webmvc.dto.ClientDTO;
import ma.fstm.ilisi.projects.webmvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // For BindingResult
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid; // Import for Valid

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Display all clients
    @GetMapping
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "index";
    }

    // Show form to create a new client
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("client", new ClientDTO());
        return "new_client";
    }

    // Save a new client
    @PostMapping
    public String saveClient(@Valid @ModelAttribute("client") ClientDTO clientDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "new_client";
        }
        clientService.saveClientDTO(clientDTO);
        return "redirect:/clients";
    }

    // Show form to edit an existing client
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        ClientDTO client = clientService.getClientDTO(id);
        model.addAttribute("client", client);
        return "edit_client";
    }

    // Update an existing client
    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable("id") int id, @ModelAttribute("client") ClientDTO clientDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "edit_client";
        }
        clientDTO.setId(id);
        clientService.saveClientDTO(clientDTO);
        return "redirect:/clients";
    }

    // Delete a client
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") int id) {
        ClientDTO clientDTO = clientService.getClientDTO(id);
        clientService.deleteClientDTO(clientDTO);
        return "redirect:/clients";
    }
}