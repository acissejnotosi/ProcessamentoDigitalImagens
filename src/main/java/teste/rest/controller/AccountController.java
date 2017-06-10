package teste.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teste.rest.Service.AccountService;
import teste.rest.model.AccountModel;
import teste.rest.model.AuthenticationModel;
import teste.rest.model.TransactionModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by allan on 02-06-2017.
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    public static Map<String, AccountModel> accounts;

    public AccountController() {
        this.accounts = new HashMap<String, AccountModel>();
    }


    @RequestMapping(path="/test", method= RequestMethod.GET)
    public ResponseEntity<?> NewClient(){
        return new ResponseEntity<>(new AccountModel(), HttpStatus.OK);
    }

    @RequestMapping(path="/account/new", method= RequestMethod.POST)
    public ResponseEntity<?> AddCliente(@RequestBody AccountModel input){
        if(input!=null&& input.getNumberAccount()!=null && input.getNumberAccount()!=null)
            return new ResponseEntity<>(accountService.createAccount(input,accounts), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(path="/account/verify", method= RequestMethod.POST)
    public ResponseEntity<?> verify(@RequestBody AccountModel input){
        if(input!=null&& input.getNumberAccount()!=null && input.getNumberAccount()!=null)
            return new ResponseEntity<>(accountService.verify(accounts,input), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(path="/account/balance", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> getBalance(@RequestBody AuthenticationModel input){
       AuthenticationModel recived = convertToEntity(input);
       if(recived.getToken()!=null)
            return new ResponseEntity<>(accountService.getBalance(recived.getToken(),accounts), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path="/account/deposit", method= RequestMethod.POST)
    public ResponseEntity<?> postDeposit(@RequestBody TransactionModel input){
        if(input!=null)
            return new ResponseEntity<>(accountService.deposit(input,accounts), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(path="/account/withdraw", method= RequestMethod.POST)
    public ResponseEntity<?> postWithDraw(@RequestBody TransactionModel input){
        if(input!=null)
            return new ResponseEntity<>(accountService.withdraw(input,accounts), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(path="/account/transfer", method= RequestMethod.POST)
    public ResponseEntity<?> postTransfer(@RequestBody TransactionModel input){
        System.out.println("aqiiio");
            return new ResponseEntity<>(accountService.transferSameBank(input,accounts), HttpStatus.OK);

    }


    @RequestMapping(path="/account/list", method= RequestMethod.GET)
    public ResponseEntity<?> getAll(){
            return new ResponseEntity<>(accountService.listAll(accounts), HttpStatus.OK);

    }

    private AuthenticationModel convertToEntity(AuthenticationModel input)  {
        AuthenticationModel post = new AuthenticationModel(input.getToken());
        return post;
    }






}
