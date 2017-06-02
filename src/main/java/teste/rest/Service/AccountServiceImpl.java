package teste.rest.Service;

import org.springframework.stereotype.Service;
import teste.rest.model.AccountModel;
import teste.rest.model.Authentication;

import java.util.*;

/**
 * Created by allan on 02-06-2017.
 */
@Service
public class AccountServiceImpl implements AccountService {


    @Override
    public Authentication createAccount(AccountModel input, Map<String, AccountModel> account) {
        String token = UUID.randomUUID().toString();
        AccountModel accountModel = new AccountModel();
        account.put(token,accountModel);
        return new Authentication(token);
    }

    @Override
    public double getBalance(AccountModel input) {
        return 0;
    }


    @Override
    public synchronized  boolean deposit(AccountModel input) {

        return false;
    }

    @Override
    public synchronized boolean withdraw(AccountModel input) {
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
}
