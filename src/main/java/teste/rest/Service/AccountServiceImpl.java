package teste.rest.Service;

import org.springframework.stereotype.Service;
import teste.rest.model.AccountModel;
import teste.rest.model.AuthenticationModel;
import teste.rest.model.TransactionModel;

import java.util.*;

/**
 * Created by allan on 02-06-2017.
 */
@Service
public class AccountServiceImpl implements AccountService {


    @Override
    public AuthenticationModel createAccount(AccountModel input, Map<String, AccountModel> account) {
        String token = UUID.randomUUID().toString();
        AccountModel accountModel = new AccountModel();
        accountModel.setAgency(input.getAgency());
        accountModel.setBalance(input.getBalance());
        accountModel.setNumberAccount(input.getNumberAccount());
        accountModel.setClientPassword(input.getClientPassword());
        account.put(token,accountModel);
        return new AuthenticationModel(token);
    }

    @Override
    public double getBalance(String token, Map<String, AccountModel> accounts) {

        return accounts.get(token).getBalance();

    }

    @Override
    public boolean transfer(TransactionModel input, Map<String, AccountModel> accounts) {
        AccountModel accountModel =accounts.get(input.getToken());
        String keys = findByAccount(accounts,input);
        if(input.getValue()>0)
            if(keys!=null)
               if(accountModel.deposit(input.getValue()));




        return false;
    }

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

    @Override
    public boolean withdraw(TransactionModel input, Map<String, AccountModel> accounts) {

        if(input.getToken()!=null) {
            AccountModel accountModel = accounts.get(input.getToken());
            if (input.getValue() > 0)
                return accountModel.withdraw(input.getValue());
        }
        return false;
    }

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

    public String findByAccount(Map<String, AccountModel> account,TransactionModel input) {

        if(input.getToken()!=null)
            return input.getToken();

        Set<String> chaves = account.keySet();
        for (Iterator<String> iterator = chaves.iterator(); iterator.hasNext();)
        {
            String chave = iterator.next();
            if(chave != null) {
                if(account.get(chave).getNumberAccount()==input.getNumberAccount())
                    if(account.get(chave).getAgency()==input.getAgency())
                        if(account.get(chave).getType().equals(input.getType()))
                           return chave;
            }
        }
        System.out.printf("Not Foud Client!!!");
        return null;


    }

    public String verifyDataforDeposit(Map<String, AccountModel> account,TransactionModel input)
    {
        return  null;


    }
}
