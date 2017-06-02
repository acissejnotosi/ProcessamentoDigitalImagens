package teste.rest.restfulbank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountDetails {
	private String clientName;
	private String clientPassword;
	private double balance;
	private Lock lock;
	
	public AccountDetails(final String clientName, final String clientPassword) {
		this.clientName = clientName;
		this.clientPassword = clientPassword;
		this.balance = 0.0;
		this.lock = new ReentrantLock();
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(final String clientName) {
		this.clientName = clientName;
	}

	public String getClientPassword() {
		return this.clientPassword;
	}

	public void setClientPassword(final String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(final double balance) {
		this.balance = balance;
	}

	public void lock() {
		this.lock.lock();
	}
	
	public void unlock() {
		this.lock.unlock();
	}
}