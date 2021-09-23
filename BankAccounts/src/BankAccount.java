import javax.swing.JOptionPane;

public class BankAccount extends Accounts{
	
	public BankAccount(String name, double balance)
	{
		super(name, balance);
	}
	
	@Override
	public void deposit(double amount) 
	{
		balance += amount;
	}
	
	@Override
	public boolean withdraw(double amount) {
		boolean flag = false;
		if(amount <= balance) { //If user has enough money to withdraw
			balance -= amount;
			flag = true;
		}
		else //Show message
			JOptionPane.showMessageDialog(null, "You don't have enough money in your balance to withdraw");
		
		return flag;		
	}
	
	@Override
	public boolean transfer(double amount, Accounts ac)
	{
		boolean flag = false;
		
		flag = withdraw(amount);
		if(flag) //If user had enough money to withdraw
			ac.deposit(amount); //Deposit the money to the receiver acount

		return flag;
	}
}