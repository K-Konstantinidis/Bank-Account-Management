import javax.swing.JOptionPane;

public class SavingsAccount extends Accounts{
	private double interestRate;

	public SavingsAccount(String name, double balance, double interestRate)
	{
		super(name, balance);
		this.interestRate = interestRate;
	}
	
	public void addInterest(double amount)
	{
		/*If balance is 0 then: If deposit is 100 and interest rate is 50 then deposit will be 100 + 100(50/100) => 150. 
		So balance will become 150 and not 100 which was the deposit*/
		double interest = amount + amount * interestRate/100;
		deposit(interest);
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
		if(flag)//If user had enough money to withdraw
			ac.deposit(amount); //Deposit the money to the receiver acount

		return flag;
	}
}
