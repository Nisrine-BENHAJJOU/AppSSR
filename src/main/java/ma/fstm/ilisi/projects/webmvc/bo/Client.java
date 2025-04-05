package ma.fstm.ilisi.projects.webmvc.bo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private double capital;

    @OneToMany(mappedBy = "client")
    private List<Commande> commandes;

    // No-Argument Constructor
    public Client() {
    }

    // Parameterized Constructor
    public Client(int id, String nom, double capital, List<Commande> commandes) {
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