package teste.rest.model;

/**
 * This class is use for simples queries from client
 * Created by allan on 02/06/17.
 */


public class AuthenticationModel {

    /**
     * This is a key which the serve send the client for identify it.
     */
    private String token;

    /**
     * Constructor from AuthenticationModel
     * @param token
     */
    public AuthenticationModel(String token) {
        this.token = token;
    }

    public AuthenticationModel() {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
