package teste.rest.restfulbank;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    private Map<Integer, AccountDetails> accounts;
    
    public BankController() {
    	this.accounts = new HashMap<Integer, AccountDetails>();
    }

    @RequestMapping("/account")
    public ReturnAccountCreation account(
    		@RequestParam(value="clientName")String clientName,
    		@RequestParam(value="clientPassword")String clientPassword) {
    	
    	Integer accountNumber = null;
		
		do {
			accountNumber = new Random().nextInt(Constants.MAX_ACCOUNT_NUMBER);
		} while (this.accounts.containsKey(accountNumber));
		
		AccountDetails accountDetails = new AccountDetails(clientName, clientPassword);
		
		this.accounts.put(accountNumber, accountDetails);
		
		System.out.println("Account " + accountNumber.toString() + " created");
		
		return new ReturnAccountCreation(accountNumber);
    }
    
    @RequestMapping("/balance")
    public ReturnAccountBalance balance(
    		@RequestParam(value="accountNumber")Integer accountNumber,
    		@RequestParam(value="clientPassword")String clientPassword) {
    	try {
			this.validateAccountNumber(accountNumber);
		} catch (BankException e) {
			e.printStackTrace();
			return new ReturnAccountBalance(0, 0, Constants.RESULT_ERROR);
		}
		
		AccountDetails accountDetails = this.accounts.get(accountNumber);
		
		try {
			this.authenticateClient(accountDetails, clientPassword);
		} catch (BankException e) {
			e.printStackTrace();
			return new ReturnAccountBalance(0, 0, Constants.RESULT_ERROR);
		}
		
		accountDetails.lock();
		double balance = accountDetails.getBalance();
		accountDetails.unlock();
		
		System.out.println("Returning balance for account " + accountNumber.toString() + ": " + balance);

		return new ReturnAccountBalance(accountNumber, balance, Constants.RESULT_OK);
    }
    
    @RequestMapping("/deposit")
    public ReturnAccountBalance deposit(
    		@RequestParam(value="accountNumber")Integer accountNumber,
    		@RequestParam(value="clientPassword")String clientPassword,
    		@RequestParam(value="targetAccountNumber")Integer targetAccountNumber,
    		@RequestParam(value="amount")double amount) {
    	try {
    		this.validateAccountNumber(accountNumber);
    		this.validateAccountNumber(targetAccountNumber);
    	} catch (BankException e) {
    		e.printStackTrace();
    		return new ReturnAccountBalance(0, 0, Constants.RESULT_ERROR);
    	}
    	
    	AccountDetails accountDetails = this.accounts.get(accountNumber);
    	
    	try {
    		this.authenticateClient(accountDetails, clientPassword);
    	} catch (BankException e) {
    		e.printStackTrace();
    		return new ReturnAccountBalance(0, 0, Constants.RESULT_ERROR);
    	}
    	
    	AccountDetails targetAccountDetails = this.accounts.get(targetAccountNumber);
    	
    	targetAccountDetails.lock();
    	targetAccountDetails.setBalance(targetAccountDetails.getBalance() + amount);
    	double balance = targetAccountDetails.getBalance();
    	targetAccountDetails.unlock();
    	
		System.out.println("Depositing " + amount + " in account " + targetAccountNumber.toString());

    	return new ReturnAccountBalance(targetAccountNumber, balance, Constants.RESULT_OK);
    }
    
    @RequestMapping("/withdraw")
    public ReturnAccountBalance withdraw(
    		@RequestParam(value="accountNumber")Integer accountNumber,
    		@RequestParam(value="clientPassword")String clientPassword,
    		@RequestParam(value="amount")double amount) {
    	try {
    		this.validateAccountNumber(accountNumber);
    	} catch (BankException e) {
    		e.printStackTrace();
    		return new ReturnAccountBalance(0, 0, Constants.RESULT_ERROR);
    	}
    	
    	AccountDetails accountDetails = this.accounts.get(accountNumber);
    	
    	try {
    		this.authenticateClient(accountDetails, clientPassword);
    	} catch (BankException e) {
    		e.printStackTrace();
    		return new ReturnAccountBalance(0, 0, Constants.RESULT_ERROR);
    	}
    	    	
    	accountDetails.lock();
    	accountDetails.setBalance(accountDetails.getBalance() - amount);
    	double balance = accountDetails.getBalance();
    	accountDetails.unlock();
    	
		System.out.println("Withdrawing " + amount + " from account " + accountNumber.toString());

    	return new ReturnAccountBalance(accountNumber, balance, Constants.RESULT_OK);
    }
    
    @RequestMapping("/transfer")
    public ReturnTransferResult transfer(
    		@RequestParam(value="fromAccountNumber")Integer fromAccountNumber,
    		@RequestParam(value="clientPassword")String clientPassword,
    		@RequestParam(value="toAccountNumber")Integer toAccountNumber,
    		@RequestParam(value="amount")double amount) {
    	try {
    		this.validateAccountNumber(fromAccountNumber);
    		this.validateAccountNumber(toAccountNumber);
    	} catch (BankException e) {
    		e.printStackTrace();
    		return new ReturnTransferResult(Constants.RESULT_ERROR);
    	}
    	
    	AccountDetails fromAccountDetails = this.accounts.get(fromAccountNumber);
    	
    	try {
    		this.authenticateClient(fromAccountDetails, clientPassword);
    	} catch (BankException e) {
    		e.printStackTrace();
    		return new ReturnTransferResult(Constants.RESULT_ERROR);
    	}
    	
    	AccountDetails toAccountDetails = this.accounts.get(toAccountNumber);
    	    	
    	fromAccountDetails.lock();
    	toAccountDetails.lock();
    	
    	fromAccountDetails.setBalance(fromAccountDetails.getBalance() - amount);
    	toAccountDetails.setBalance(toAccountDetails.getBalance() + amount);
    	
    	fromAccountDetails.unlock();
    	toAccountDetails.unlock();
    	
		System.out.println("Transferring " + amount + " from account " + fromAccountNumber.toString() + " to account " + toAccountNumber.toString());

		return new ReturnTransferResult(Constants.RESULT_OK);
    }
    
	private void validateAccountNumber(Integer accountNumber) throws BankException {
		if (!this.accounts.containsKey(Integer.valueOf(accountNumber))) {
			throw new BankException(BankException.ACCOUT_NOT_FOUND);
		}
	}
	
	private void authenticateClient(AccountDetails accountDetails, String clientPassword)  throws BankException {
		if (!clientPassword.equals(accountDetails.getClientPassword())) {
			throw new BankException(BankException.WRONG_PASSWORD);
		}
	}
}