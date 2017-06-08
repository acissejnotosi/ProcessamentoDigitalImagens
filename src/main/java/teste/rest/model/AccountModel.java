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
    private Integer type;


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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public synchronized boolean deposit(double value){
        balance = balance + value;
        return true;
    }
    public synchronized boolean withdraw(double value){
        if(balance - value>0) {
            balance = balance - value;
            return true;
        }else{
            return true;
        }
    }
    public synchronized double getbalance(double value){
       return value;
    }
}
