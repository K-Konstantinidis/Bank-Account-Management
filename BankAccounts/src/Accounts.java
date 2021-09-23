public abstract class Accounts {
	protected String name;
	protected double balance;
	
	public Accounts(String name, double balance)
	{
		this.name = name;
		this.balance = balance;
	}
	
	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}

	public abstract void deposit(double amount);
	public abstract boolean withdraw(double amount);
	public abstract boolean transfer(double amount, Accounts ac);
}
