package teste.rest.restfulbank;

public class ReturnAccountBalance {
	private final Integer accountNumber;
    private final double balance;
    private final int result;

	public ReturnAccountBalance(Integer accountNumber, double balance, int result) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.result = result;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public int getResult() {
		return result;
	}
}
