package teste.rest.Service;

import org.springframework.stereotype.Service;
import teste.rest.model.AccountModel;

/**
 * Created by allan on 02-06-2017.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public AccountModel createAccount(AccountModel input) {
        return input;
    }

    @Override
    public double getBalance(AccountModel input) {
        return 0;
    }
}
