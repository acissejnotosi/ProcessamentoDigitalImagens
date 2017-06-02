package teste.rest.restfulbank;

public class BankException extends Exception {
	private static final long serialVersionUID = -2148639847885188569L;
	
	public static final String ACCOUT_NOT_FOUND = "Account not found";
	public static final String WRONG_PASSWORD = "Wrong password";
	
	public BankException(String message) {
		super(message);
	}
}