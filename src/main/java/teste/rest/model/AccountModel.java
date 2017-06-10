package teste.rest.model;

/**
 * Is a account represent each client
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

    /**
     * This method do deposit in this account
     * @param value is value the client put to despoit
     * @return boolean
     * @apiNote this method is synchronized
     */
    public synchronized boolean deposit(double value){
        balance = balance + value;
        return true;
    }

    /**
     * This method do withdraw from this account
     * @param value
     * @return boolean
     */
    public synchronized boolean withdraw(double value){
        if(balance - value>0) {
            balance = balance - value;
            return true;
        }else{
            return false;
        }
    }
    /**
     * This method restur the value of balance this account
     * @param value
     * @return double
     */
    public synchronized double getbalance(double value){
        return value;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }



}
