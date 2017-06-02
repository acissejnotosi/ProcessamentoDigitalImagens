package teste.rest.Service;

import teste.rest.model.AccountModel;

/**
 * Created by allan on 02-06-2017.
 */
public interface AccountService {

    public AccountModel createAccount(AccountModel input);
    public double getBalance(AccountModel input);
}
