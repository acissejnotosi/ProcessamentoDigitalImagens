package teste.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import teste.rest.model.AccountModel;

/**
 * Created by allan on 02-06-2017.
 */
@RestController
public class AccountController {


    @RequestMapping(path="/test", method= RequestMethod.GET)
    public ResponseEntity<?> NewClient(){

        return new ResponseEntity<>(new AccountModel(), HttpStatus.OK);
    }


}
