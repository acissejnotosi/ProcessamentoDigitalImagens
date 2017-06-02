package teste.rest.model;

/**
 * Created by allan on 02-06-2017.
 */
public class AccountModel {
    private int id;
    private Integer numberAccount;
    private String  clientPassword;
    private double  balance;
    private Integer agency;
    private Integer bank;

    public AccountModel() {
        this.id = 1;
        this.numberAccount = 1249;
        this.clientPassword = "password";
        this.balance= 2.0;
        this.agency = 1515;
        this.bank =1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(Integer numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Integer getAgency() {
        return agency;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    public Integer getBank() {
        return bank;
    }

    public void setBank(Integer bank) {
        this.bank = bank;
    }


}
