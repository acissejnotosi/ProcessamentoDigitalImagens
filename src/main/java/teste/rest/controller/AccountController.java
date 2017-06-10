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
 * Rest Controller recive the request from the clients by json
 * and answord by json too.
 * Created by allan on 02-06-2017.
 */
@RestController
public class AccountController {
    /**
     * Instance the service
     */
    @Autowired
    private AccountService accountService;


    public static Map<String, AccountModel> accounts;
    /**
     * Constructor instance a Map of AccountModel when the server is instantiated
     */
    public AccountController() {
        this.accounts = new HashMap<String, AccountModel>();
    }



    /**
     * Create a new client or just sign in account already exist
     * @RequestMethod POST
     * @param input type AccountModel
     * @return AuthenticationModel
     */

    @RequestMapping(path="/account/new", method= RequestMethod.POST)
    public ResponseEntity<?> AddCliente(@RequestBody AccountModel input){
        if(input!=null&& input.getNumberAccount()!=null && input.getNumberAccount()!=null)
            return new ResponseEntity<>(accountService.createAccount(input,accounts), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    /**
     * Verify if the client already exist
     * @RequestMethod POST
     * @param input type AccountModel
     * @return boolean
     */

    @RequestMapping(path="/account/verify", method= RequestMethod.POST)
    public ResponseEntity<?> verify(@RequestBody AccountModel input){
        if(input!=null&& input.getNumberAccount()!=null && input.getNumberAccount()!=null)
            return new ResponseEntity<>(accountService.verify(accounts,input), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Create a new client or just sing account already existence
     * @RequestMethod POST
     * @param input type AuthenticationModel
     * @return boolean
     *
     */
    @RequestMapping(path="/account/balance", method= RequestMethod.POST)
    public ResponseEntity<?> getBalance(@RequestBody AuthenticationModel input){
       AuthenticationModel recived = convertToEntity(input);
       if(recived.getToken()!=null)
            return new ResponseEntity<>(accountService.getBalance(recived.getToken(),accounts), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Deposit in the account specified in TransationModel
     * @RequestMethod POST
     * @param input type AccountModel
     * @return boolean
     *
     */
    @RequestMapping(path="/account/deposit", method= RequestMethod.POST)
    public ResponseEntity<?> postDeposit(@RequestBody TransactionModel input){
        if(input!=null)
            return new ResponseEntity<>(accountService.deposit(input,accounts), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    /**
     * Withdraw from the account from the client who request the method
     * @param input type AccountModel
     * @return boolean
     * @apiNote POST
     */
    @RequestMapping(path="/account/withdraw", method= RequestMethod.POST)
    public ResponseEntity<?> postWithDraw(@RequestBody TransactionModel input){
        if(input!=null)
            return new ResponseEntity<>(accountService.withdraw(input,accounts), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Transfer the money from client request to another specified in the TransactionModel
     * @param input type TransactionModel
     * @return boolean
     * @apiNote POST
     */
    @RequestMapping(path="/account/transfer", method= RequestMethod.POST)
    public ResponseEntity<?> postTransfer(@RequestBody TransactionModel input){
        if(input!=null)
            return new ResponseEntity<>(accountService.transferSameBank(input,accounts), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    /**
     * List all accounts not use yet
     * @return List AuthenticationModel
     * @apiNote POST
     */
    @RequestMapping(path="/account/list", method= RequestMethod.GET)
    public ResponseEntity<?> getAll(){
            return new ResponseEntity<>(accountService.listAll(accounts), HttpStatus.OK);

    }
    /**
     * Map the json unknown to entity
     * @param input type AccountModel
     * @return AuthenticationModel
     */

    private AuthenticationModel convertToEntity(AuthenticationModel input)  {
        AuthenticationModel post = new AuthenticationModel(input.getToken());
        return post;
    }






}
