package ma.fstm.ilisi.projects.webmvc.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;
import ma.fstm.ilisi.projects.webmvc.bo.Commande;

public class ClientDTO {
    private int id;

    @NotBlank(message = "Name is required")
    private String nom;

    @Positive(message = "Capital must be positive")
    private double capital;

    private List<Commande> commandes;

    // No-Argument Constructor
    public ClientDTO() {
    }

    // Parameterized Constructor
    public ClientDTO(int id, String nom, double capital, List<Commande> commandes) {
        this.id = id;
        this.nom = nom;
        this.capital = capital;
        this.commandes = commandes;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}