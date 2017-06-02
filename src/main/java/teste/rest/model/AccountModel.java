package teste.rest.model;

/**
 * Created by allan on 02-06-2017.
 */
public class AccountModel {
    private int id;
    private String clientName;
    private String clientPassword;

    public AccountModel() {
        this.id = 1;
        this.clientName = "Client Test";
        this.clientPassword = "password";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }
}
