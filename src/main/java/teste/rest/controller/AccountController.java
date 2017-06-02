package teste.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import teste.rest.Service.AccountService;
import teste.rest.model.AccountModel;

/**
 * Created by allan on 02-06-2017.
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(path="/test", method= RequestMethod.GET)
    public ResponseEntity<?> NewClient(){
        return new ResponseEntity<>(new AccountModel(), HttpStatus.OK);
    }

    @RequestMapping(path="/account/new", method= RequestMethod.POST)
    public ResponseEntity<?> AddCliente(AccountModel input){
        if(input!=null&& input.getClientName()!=null && input.getClientName()!=null)
            return new ResponseEntity<>(accountService.createAccount(input), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(path="/account/balance", method= RequestMethod.POST)
    public ResponseEntity<?> getBalance(AccountModel input){
        if(input!=null&& input.getClientName()!=null && input.getClientName()!=null)
            return new ResponseEntity<>(accountService.createAccount(input), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
