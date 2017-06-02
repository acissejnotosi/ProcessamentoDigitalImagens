package teste.rest.restfulbank;

public class ReturnAccountCreation {
	private final Integer accountNumber;
	
	public ReturnAccountCreation(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}
}
