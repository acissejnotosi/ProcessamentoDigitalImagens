package teste.rest.restfulbank;

public class Constants {
	public static final int MAX_ACCOUNT_NUMBER = 1000;
	public static final int RESULT_OK = 0;
	public static final int RESULT_ERROR = -1;
	
	public static final String CREATE_ACCOUNT_URL = "http://localhost:8080/restful-bank/account?clientName=%s&clientPassword=%s";
	public static final String GET_BALANCE_URL = "http://localhost:8080/restful-bank/balance?accountNumber=%s&clientPassword=%s";
	public static final String DEPOSIT_URL = "http://localhost:8080/restful-bank/deposit?accountNumber=%s&clientPassword=%s&targetAccountNumber=%s&amount=%s";
	public static final String WITHDRAW_URL = "http://localhost:8080/restful-bank/withdraw?accountNumber=%s&clientPassword=%s&amount=%s";
	public static final String TRANSFER_URL = "http://localhost:8080/restful-bank/transfer?fromAccountNumber=%s&clientPassword=%s&toAccountNumber=%s&amount=%s";
}
