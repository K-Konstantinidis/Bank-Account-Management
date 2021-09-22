import javax.swing.JOptionPane;

public class BankAccount {
	protected String name;
	protected double balance;
	
	public BankAccount(String name, double balance)
	{
		this.name = name;
		this.balance = balance;
	}
	
	public void deposit(double amount) 
	{
		balance += amount;
	}
	
	public boolean withdraw(double amount) 
	{
		boolean flag = false;
		
		if(amount <= balance) {
			balance -= amount;
			flag = true;
		}
		else
			JOptionPane.showMessageDialog(null, "You don't have enough money in your balance to withdraw");
		
		return flag;
	}
	
	public boolean transfer(double amount, BankAccount ac)
	{
		boolean flag = false;
		
		flag = withdraw(amount);
		if(flag)
			ac.deposit(amount);

		return flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}	
}