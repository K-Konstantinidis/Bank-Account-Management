public class SavingsAccount extends BankAccount{
	private double interestRate;
	
	public SavingsAccount(String name, double balance, double interestRate)
	{
		super(name, balance);
		this.interestRate = interestRate;
	}
	
	public void addInterest(double balance)
	{
		double interest = balance * interestRate/100;
		deposit(interest);
	}
}
