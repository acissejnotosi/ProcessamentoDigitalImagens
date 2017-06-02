package teste.rest.Service;

import teste.rest.model.AccountModel;
import teste.rest.model.Authentication;

import java.util.List;
import java.util.Map;

/**
 * Created by allan on 02-06-2017.
 */
public interface AccountService {

    public Authentication createAccount(AccountModel input, Map<String, AccountModel> accounts);
    public double getBalance(AccountModel input);
    public boolean deposit(AccountModel input);
    public boolean  withdraw(AccountModel input);
    public List<AccountModel> listAll(Map<String, AccountModel> account);
}
