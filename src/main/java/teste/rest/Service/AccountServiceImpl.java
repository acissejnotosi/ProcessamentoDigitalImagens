package teste.rest.Service;

import org.springframework.stereotype.Service;
import teste.rest.model.AccountModel;
import teste.rest.model.AuthenticationModel;
import teste.rest.model.TransactionModel;

import java.util.*;

/**
 * Implement the business rules of account
 * Created by allan on 02-06-2017.
 */
@Service
public class AccountServiceImpl implements AccountService {

    /**
     * Instace the new client accout
     * @param account
     * @param input
     * @return AuthenticationModel
     */
    @Override
    public AuthenticationModel createAccount(AccountModel input, Map<String, AccountModel> account) {
        TransactionModel t = new TransactionModel();
        t.setAgency(input.getAgency());
        t.setNumberAccount(input.getNumberAccount());
        String key = findByAccount(account,t);
        if(key==null) {
            String token = UUID.randomUUID().toString();
            AccountModel accountModel = new AccountModel();
            accountModel.setAgency(input.getAgency());
            accountModel.setBalance(input.getBalance());
            accountModel.setNumberAccount(input.getNumberAccount());
            accountModel.setBank(input.getBank());
            accountModel.setType(input.getType());
            accountModel.setClientPassword(input.getClientPassword());
            account.put(token, accountModel);
            return new AuthenticationModel(token);
        }else{
            if(account.get(key).getClientPassword().equals(input.getClientPassword()))
                if (account.get(key).getBank().equals(input.getBank()))
                    if (account.get(key).getType().equals(input.getType()))
                            return new AuthenticationModel(key);
        }
        return null;
    }
    /**
     * Get balance from a account
     * @param accounts
     * @param token
     * @return double
     */
    @Override
    public double getBalance(String token, Map<String, AccountModel> accounts) {

        return accounts.get(token).getBalance();

    }

    /**
     * Trasnfer money between accounts
     * @param input
     * @param accounts
     * @return boolean
     */
    @Override
    public boolean transferSameBank(TransactionModel input, Map<String, AccountModel> accounts) {
        AccountModel cliente  = accounts.get(input.getToken());
        if(input.getBank()==cliente.getBank())
         if(input.getType().equals(cliente.getType())){
             return transfer(accounts, input,input.getValue());
         }
        return false;


    }

    /**
     * Transfer money between banks
     * @param input
     * @param accounts
     * @return boolean
     */
    @Override
    public boolean transferBetwenbBanks(TransactionModel input, Map<String, AccountModel> accounts) {
        AccountModel cliente  = accounts.get(input.getToken());
        if(input.getBank()==cliente.getBank())
            if(input.getType()==cliente.getType()){
                return transfer(accounts, input,input.getValue()+4.30);
            }
        return false;

    }

    /**
     * Desposit money from someone have the account number, agency, and type.
     * @param input
     * @param accounts
     * @return boolean
     */

    @Override
    public  boolean deposit(TransactionModel input,Map<String, AccountModel> accounts) {

        if(input.getToken()==null) {
            String keys = findByAccount(accounts, input);
            AccountModel accountModel = accounts.get(keys);
            if(input.getValue()>0&&keys!=null){
               return accountModel.deposit(input.getValue());
            }
        }else{
            AccountModel accountModel = accounts.get(input.getToken());
            if(input.getValue()>0){
                return accountModel.deposit(input.getValue());
            }
        }


        return false;
    }

    /**
     * Withdraw the account from account with have a token
     * @param input
     * @param accounts
     * @return boolean
     */
    @Override
    public boolean withdraw(TransactionModel input, Map<String, AccountModel> accounts) {

        if(input.getToken()!=null) {
            AccountModel accountModel = accounts.get(input.getToken());
            if (input.getValue() > 0)
                return accountModel.withdraw(input.getValue());
        }
        return false;
    }

    /**
     * List all Account this only for admin or debug
     * @param account
     * @return list
     */

    @Override
    public List<AccountModel> listAll(Map<String, AccountModel> account) {
        List<AccountModel> accountModelList = new ArrayList<>();

        Set<String> chaves = account.keySet();
        for (Iterator<String> iterator = chaves.iterator(); iterator.hasNext();)
        {
            String chave = iterator.next();
            if(chave != null) {
                System.out.println(chave + account.get(chave));
                accountModelList.add(account.get(chave));
            }
        }
        return accountModelList;
    }

    /**
     * Search  in hashMap the account by numberAccount and agencyAccount
     * @param account
     * @param input
     * @return String is  token from the hash where the user are stored
     */

    public String findByAccount(Map<String, AccountModel> account,TransactionModel input) {

        if(input.getToken()!=null)
            return input.getToken();

            Set<String> chaves = account.keySet();
        for (Iterator<String> iterator = chaves.iterator(); iterator.hasNext();)
        {
            String chave = iterator.next();
            if(chave != null) {
                if(account.get(chave).getNumberAccount().equals(input.getNumberAccount()))
                    if(account.get(chave).getAgency().equals(input.getAgency()))
                           return chave;
            }
        }
        System.out.printf("Not Foud Client!!!");
        return null;


    }

    /**
     * Search in hashMap the account by password, bank, type
     * @param account
     * @param input
     * @return String is  token from the hash where the user are stored
     */


    public String verify(Map<String, AccountModel> account,AccountModel input)
    {
        TransactionModel t = new TransactionModel();
        t.setAgency(input.getAgency());
        t.setNumberAccount(input.getNumberAccount());
        String key = findByAccount(account,t);
        if(key!=null)
            if(account.get(key).getClientPassword().equals(input.getClientPassword()))
                if (account.get(key).getBank().equals(input.getBank()))
                    if (account.get(key).getType()==input.getType())
                        return key;
        return "false";

    }

    /**
     * Just implement the tranfer
     * @param accounts
     * @param input
     * @param amout
     * @return boolean
     */
    public boolean transfer (Map<String, AccountModel> accounts,TransactionModel input, double amout){

        if(input.getToken()!=null) {
            String to = input.getToken();
            input.setToken(null);
            String keys = findByAccount(accounts, input);
            AccountModel accountModel = accounts.get(keys);
            AccountModel accountModel2= accounts.get(to);
            if(input.getValue()>0&&keys!=null){
                if(accountModel2.withdraw(amout))
                   return accountModel.deposit(input.getValue());

            }

        }
        return false;
    }
}
