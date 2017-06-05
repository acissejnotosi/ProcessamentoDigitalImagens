package teste.rest.model;

/**
 * Created by allan on 02/06/17.
 */


public class AuthenticationModel {
    private String token;

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
