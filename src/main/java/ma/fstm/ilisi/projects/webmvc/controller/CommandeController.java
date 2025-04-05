package ma.fstm.ilisi.projects.webmvc.controller;

import ma.fstm.ilisi.projects.webmvc.dto.ClientDTO;
import ma.fstm.ilisi.projects.webmvc.dto.CommandeDTO;
import ma.fstm.ilisi.projects.webmvc.service.ClientService;
import ma.fstm.ilisi.projects.webmvc.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private ClientService clientService;

    // Display all orders
    @GetMapping
    public String listCommandes(Model model) {
        model.addAttribute("commandes", commandeService.getAllCommandes());
        return "commande_list";
    }

    // Show form to create a new order
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("commande", new CommandeDTO());
        model.addAttribute("clients", clientService.getAllClients());
        return "new_commande";
    }

    // Save a new order
    @PostMapping
    public String saveCommande(@Valid @ModelAttribute("commande") CommandeDTO commandeDTO, BindingResult result, @RequestParam("clientId") int clientId, Model model) {
        System.out.println("Received clientId: " + clientId);
        System.out.println("CommandeDTO before validation: " + commandeDTO);

        // Manual validation for clientId
        if (clientId <= 0) {
            result.addError(new FieldError("commande", "client", "Please select a client"));
        }

        if (result.hasErrors()) {
            System.out.println("Validation errors: " + result.getAllErrors());
            model.addAttribute("clients", clientService.getAllClients());
            return "new_commande";
        }

        ClientDTO client = clientService.getClientDTO(clientId);
        commandeDTO.setClient(client);
        commandeDTO.setDate(new Date());
        System.out.println("CommandeDTO after setting client and date: " + commandeDTO);

        try {
            commandeService.saveCommandeDTO(commandeDTO);
            System.out.println("Commande saved successfully");
        } catch (Exception e) {
            System.out.println("Error saving commande: " + e.getMessage());
            e.printStackTrace();
            result.addError(new FieldError("commande", "client", "Error saving order: " + e.getMessage()));
            model.addAttribute("clients", clientService.getAllClients());
            return "new_commande";
        }

        return "redirect:/commandes";
    }

    // Show form to edit an existing order
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        CommandeDTO commande = commandeService.getCommandeDTO(id);
        model.addAttribute("commande", commande);
        model.addAttribute("clients", clientService.getAllClients());
        return "edit_commande";
    }

    // Update an existing order
    @PostMapping("/update/{id}")
    public String updateCommande(@PathVariable("id") int id, @Valid @ModelAttribute("commande") CommandeDTO commandeDTO, BindingResult result, @RequestParam("clientId") int clientId, Model model) {
        // Manual validation for clientId
        if (clientId <= 0) {
            result.addError(new FieldError("commande", "client", "Please select a client"));
        }

        if (result.hasErrors()) {
            model.addAttribute("clients", clientService.getAllClients());
            return "edit_commande";
        }

        commandeDTO.setId(id);
        ClientDTO client = clientService.getClientDTO(clientId);
        commandeDTO.setClient(client);
        commandeService.saveCommandeDTO(commandeDTO);
        return "redirect:/commandes";
    }

    // Delete an order
    @GetMapping("/delete/{id}")
    public String deleteCommande(@PathVariable("id") int id) {
        CommandeDTO commandeDTO = commandeService.getCommandeDTO(id);
        commandeService.deleteCommandeDTO(commandeDTO);
        return "redirect:/commandes";
    }
}