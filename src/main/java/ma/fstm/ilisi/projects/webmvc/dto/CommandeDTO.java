package ma.fstm.ilisi.projects.webmvc.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class CommandeDTO {
    private int id;

    private Date date;

    // Removed @NotNull since we're setting this manually in the controller
    private ClientDTO client;

    // Constructors, Getters, and Setters
    public CommandeDTO() {
    }

    public CommandeDTO(int id, Date date, ClientDTO client) {
        this.id = id;
        this.date = date;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }
}