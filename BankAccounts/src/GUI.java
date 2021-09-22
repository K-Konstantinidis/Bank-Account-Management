import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	
	JFrame frame = new JFrame();

	CardLayout layout = new CardLayout();
	
	JPanel deck = new JPanel();
	
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	
	JTextField name, balance, inrate, amount, acount;
	JButton addCustomerBA, addCustomerSA, login, deposit, withdraw, transfer, backtomain;
	JLabel label, label2, label3, label4, username, userbalance, amountlabel, acountlabel;
	
	ArrayList<BankAccount> baccounts = new ArrayList<BankAccount>();
	ArrayList<SavingsAccount> saccounts = new ArrayList<SavingsAccount>();
	
	public GUI() {
		deck.setLayout(layout);
		
		panel.setLayout(null);
		panel2.setLayout(null);
		
		deck.add(panel, "first"); //Add panels to deck
		deck.add(panel2, "second");
		
		//Label and field for name, balance & inrate
		label = new JLabel("Full Name");
		label.setBounds(10, 10, 100, 20);
		panel.add(label);
		
		name = new JTextField();
		name.setBounds(120, 10, 150, 20);
		panel.add(name);
		
		label2 = new JLabel("Balance");
		label2.setBounds(10, 40, 100, 20);
		panel.add(label2);
		
		balance = new JTextField();
		balance.setBounds(120, 40, 150, 20);
		panel.add(balance);
		
		label3 = new JLabel("Interest rate");
		label3.setBounds(10, 70, 100, 20);
		panel.add(label3);
		
		inrate = new JTextField();
		inrate.setBounds(120, 70, 150, 20);
		panel.add(inrate);
		
		label4 = new JLabel("*Add interest rate only for the Savings Account");
		label4.setBounds(10, 95, 400, 10);
		label4.setFont(new Font("SAN_SERIF", Font.BOLD, 9));
		label4.setForeground(Color.red);
		panel.add(label4);
		
		//Add the 3 buttons		
		addCustomerBA = new JButton("Add Customer to Bank Account");
		addCustomerBA.setBounds(280, 25, 250, 20);
		panel.add(addCustomerBA);
		
		addCustomerSA = new JButton("Add Customer to Savings Account");
		addCustomerSA.setBounds(280, 55, 250, 20);
		panel.add(addCustomerSA);
		
		login = new JButton("Log In");
		login.setBounds(250, 125, 100, 20);
		panel.add(login);

		addCustomerBA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name1 = name.getText(); //Get name
				String balance1 = balance.getText(); //Get balance
				boolean flag = false;

				if(name1.isEmpty() || balance1.isEmpty()) { //If either name or balace are empty show message
					if(name1.isEmpty()) {
						JOptionPane.showMessageDialog(null, "You can't leave Full Name empty");
					}else
						JOptionPane.showMessageDialog(null, "You can't leave Balance empty");
				}
				else {
					if(isNumeric(balance1)) { //If balance is a number
						if(baccounts.isEmpty()) { //If Bank Accounts list is empty add 1st user
							baccounts.add(new BankAccount(name1, Double.parseDouble(balance1)));
							JOptionPane.showMessageDialog(null, "New Bank Account Created");
						}
						else {
							for(BankAccount b : baccounts) { //For every account in list with Bank Accounts
								if(b.getName().equals(name1)) { //If you find the given name
									flag = true;
									break;
								}
							}
							if(!flag) { //If name doesn't exist, then add the new user
								baccounts.add(new BankAccount(name1, Double.parseDouble(balance1)));
								JOptionPane.showMessageDialog(null, "New Bank Account Created");
							}
							else { //Show message
								JOptionPane.showMessageDialog(null, "User already exists");
							}
						}
					}
					else { //Show message
						JOptionPane.showMessageDialog(null, "Balance must be a number");
					}
				}
			}
		});
		
		addCustomerSA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name1 = name.getText(); //Get name
				String balance1 = balance.getText(); //Get balance
				String inrate1 = inrate.getText(); //Get inrate
				boolean flag = false;
				
				if(name1.isEmpty() || balance1.isEmpty() || inrate1.isEmpty()) { //If either name, balace or inrate are empty show message
					if(name1.isEmpty()) {
						JOptionPane.showMessageDialog(null, "You can't leave Full Name empty");
					}else if(inrate1.isEmpty()) {
						JOptionPane.showMessageDialog(null, "You can't leave Inrate empty");
					}else
						JOptionPane.showMessageDialog(null, "You can't leave Balance empty");
				}
				else {
					if(isNumeric(balance1) && isNumeric(inrate1)) { //If balance and inrate are numbers
						if(saccounts.isEmpty()) { //If Savings Accounts list is empty add 1st user
							saccounts.add(new SavingsAccount(name1, Double.parseDouble(balance1), Double.parseDouble(inrate1)));
							JOptionPane.showMessageDialog(null, "New Savings Account Created");
						}
						else {
							for(SavingsAccount s : saccounts) { //For every account in list with Savings Accounts
								if(s.getName().equals(name1)) { //If you find the given name
									flag = true;
									break;
								}
							}
							if(!flag) { //If name doesn't exist, then add the new user
								saccounts.add(new SavingsAccount(name1, Double.parseDouble(balance1), Double.parseDouble(inrate1)));
								JOptionPane.showMessageDialog(null, "New Savings Account Created");
							}
							else { //Show Message
								JOptionPane.showMessageDialog(null, "User already exists");
							}
						}
					}
					else { //Show Message
						if(!isNumeric(inrate1)) {
							JOptionPane.showMessageDialog(null, "Inrate must be a number");
						}else
							JOptionPane.showMessageDialog(null, "Balance must be a number");
					}
				}
			}
		});
		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name1 = name.getText(); //Get name
				name.setText("");
				balance.setText("");
				inrate.setText("");
				boolean flag = false;
				boolean flag1 = false;
				BankAccount bank = null;
				SavingsAccount save = null;
				
				if(name1.isEmpty()) { //If name is empty show message
					JOptionPane.showMessageDialog(null, "You can't leave Full Name empty");
				}
				else {
					if(baccounts.isEmpty() && saccounts.isEmpty()) { //If there are no users in the lists
						JOptionPane.showMessageDialog(null, "User: '" + name1 + "' does not have an account.");
					}
					else if(saccounts.isEmpty()) { //If savings list is empty no need to check there
						for(BankAccount b : baccounts) {
							if(b.getName().equals(name1)) {
								flag = true;
								bank = b;
								break;
							}
						}
					}
					else if(baccounts.isEmpty()) { //If bank list is empty no need to check there
						for(SavingsAccount s : saccounts) {
							if(s.getName().equals(name1)) {
								flag1 = true;
								save = s;
								break;
							}
						}
					}				
					else { //Check both lists to find the user
						for(BankAccount b : baccounts) {
							if(b.getName().equals(name1)) {
								flag = true;
								bank = b;
								break;
							}
						}
						
						if(flag) { //If you found the user in bank accounts list no need to check the savings account list
							for(SavingsAccount s : saccounts) {
								if(s.getName().equals(name1)) {
									flag1 = true;
									save = s;
									break;
								}
							}
						}
					}
					if(flag || flag1) { //If you found the user
						username = new JLabel("User name: " + name1); //Show their name
						username.setBounds(150, 5, 150, 20);
						username.setForeground(Color.BLUE);
						panel2.add(username);
							
						boolean flag2 = flag;
						Double blc = null;
							
						if(save == null) {
							blc = bank.balance;
						}
						else {
							blc = save.balance;
						}
							
						userbalance = new JLabel("User Balance: " + blc + "$"); //Show their balance
						userbalance.setBounds(300, 5, 150, 20);
						userbalance.setForeground(Color.BLUE);
						panel2.add(userbalance);
							
						//Label and field for the amount
						amountlabel = new JLabel("Amount");
						amountlabel.setBounds(125, 35, 130, 20);
						panel2.add(amountlabel);
						
						amount = new JTextField();
						amount.setBounds(210, 35, 100, 20);
						panel2.add(amount);
							
						//Add the 3 buttons
						deposit = new JButton("Make A Deposit");
						deposit.setBounds(150, 65, 200, 20);
						panel2.add(deposit);

						withdraw = new JButton("Withdraw Money");
						withdraw.setBounds(150, 95, 200, 20);
						panel2.add(withdraw);
						
						//Label and field for the account
						acountlabel = new JLabel("Transfer Account");
						acountlabel.setBounds(100, 125, 130, 20);
						panel2.add(acountlabel);
						
						acount = new JTextField();
						acount.setBounds(210, 125, 100, 20);
						panel2.add(acount);
							
						transfer = new JButton("Transfer Money");
						transfer.setBounds(150, 155, 200, 20);
						panel2.add(transfer);
						
						backtomain = new JButton("Go Back");
						backtomain.setBounds(400, 80, 100, 20);
						panel2.add(backtomain);
						
						backtomain.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								layout.show(deck, "first");
							}
						});
							
						deposit.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String money = amount.getText(); //Get amount
								amount.setText("");
								boolean flag3 = flag2;

								if(money.isEmpty()) { //If amount is empty
									JOptionPane.showMessageDialog(null, "You can't leave Amount empty");
								}
								else {
									if(isNumeric(money)) { //If amount is a number
										if(flag3) { //Find user in bank accounts list
											for(BankAccount b : baccounts) {
												if(b.getName().equals(name1)) {
													b.deposit(Double.parseDouble(money));
													JOptionPane.showMessageDialog(null, "Deposit made" + "\n" + "New balance: " + b.getBalance() + "$");
													userbalance.setText("User Balance: " + b.getBalance() + "$"); //Show their balance
													break;
												}
											}
										}
										else { //Find user in savings accounts list
											for(SavingsAccount s : saccounts) {
												if(s.getName().equals(name1)) {
													s.addInterest((Double.parseDouble(money)));
													JOptionPane.showMessageDialog(null, "Deposit made." + "\n" + "Interest added in your deposit" + "\n" + "New balance: " + s.getBalance() + "$");
													userbalance.setText("User Balance: " + s.getBalance() + "$"); //Show their balance
													break;
												}
											}
										}
									}
									else //Show message
										JOptionPane.showMessageDialog(null, "Amount must be a number");
								}
							}
						});
							
						withdraw.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String money = amount.getText(); //Get amount
								amount.setText("");
								boolean flag3 = flag2;
								boolean withd = false;

								if(money.isEmpty()) { //If amount is empty
									JOptionPane.showMessageDialog(null, "You can't leave Amount empty");
								}
								else {
									if(isNumeric(money)) { //If amount is a number
										if(flag3) { //Find user in bank accounts list
											for(BankAccount b : baccounts) {
												if(b.getName().equals(name1)) {
													withd = b.withdraw(Double.parseDouble(money));
													if(withd) {
														JOptionPane.showMessageDialog(null, "Money withdrawn" + "\n" + "New balance: " + b.getBalance() + "$");
														userbalance.setText("User Balance: " + b.getBalance() + "$"); //Show their balance
														break;
													}
													break;
												}
											}
										}
										else { //Find user in savings accounts list
											for(SavingsAccount s : saccounts) {
												if(s.getName().equals(name1)) {
													withd = s.withdraw(Double.parseDouble(money));
													if(withd) {
														JOptionPane.showMessageDialog(null, "Money withdrawn" + "\n" + "New balance: " + s.getBalance() + "$");
														userbalance.setText("User Balance: " + s.getBalance() + "$"); //Show their balance
													}
													break;
												}
											}
										}
									}
									else //Show message
										JOptionPane.showMessageDialog(null, "Amount must be a number");
								}
							}
						});
							
						transfer.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String money = amount.getText(); //Get amount
								amount.setText("");
								String sendto = acount.getText(); //Get acount
								acount.setText("");
								boolean flag3 = flag2;
								boolean flag4 = false;
								boolean flag5 = false;
								boolean trans = false;
								
								if(money.isEmpty() || sendto.isEmpty()) { //If either amount or acount are empty
									if(sendto.isEmpty()) {
										JOptionPane.showMessageDialog(null, "You can't leave Transfer Acount empty");
									}else
										JOptionPane.showMessageDialog(null, "You can't leave Amount empty");
								}
								else {
									if(isNumeric(money)) { //If amount is a number
										if(flag3) { //Search in bank accounts list
											for(BankAccount b : baccounts) {
												if(b.getName().equals(name1)) {
													for(BankAccount send : baccounts) {
														if(send.getName().equals(sendto)) {
															trans = b.transfer(Double.parseDouble(money), send);
															if(trans) {
																JOptionPane.showMessageDialog(null, "Money transfered" + "\n" + "New balance: " + b.getBalance() + "$");
																userbalance.setText("User Balance: " + b.getBalance() + "$"); //Show their balance
															}
															flag4 = true;
															break;
														}
													}
													if(!flag4) {
														for(SavingsAccount send : saccounts) {
															if(send.getName().equals(sendto)) {
																trans = b.transfer(Double.parseDouble(money), send);
																if(trans) {
																	JOptionPane.showMessageDialog(null, "Money transfered" + "\n" + "New balance: " + b.getBalance() + "$");
																	userbalance.setText("User Balance: " + b.getBalance() + "$"); //Show their balance
																}
																flag5 = true;
																break;
															}
														}
													}
													if(!flag4 && !flag5) {
														JOptionPane.showMessageDialog(null, "User: '" + sendto + "' does not have an account.");
													}
												}
											}
										}
										else { //Search in savings accounts list
											for(SavingsAccount s : saccounts) {
												if(s.getName().equals(name1)) {
													for(BankAccount send : baccounts) {
														if(send.getName().equals(sendto)) {
															trans = s.transfer(Double.parseDouble(money), send);
															if(trans) {
																JOptionPane.showMessageDialog(null, "Money transfered" + "\n" + "New balance: " + s.getBalance() + "$");
																userbalance.setText("User Balance: " + s.getBalance() + "$"); //Show their balance
															}
															flag4 = true;
															break;
														}
													}
													if(!flag4) {
														for(SavingsAccount send : saccounts) {
															if(send.getName().equals(sendto)) {
																trans = s.transfer(Double.parseDouble(money), send);
																if(trans) {
																	JOptionPane.showMessageDialog(null, "Money transfered" + "\n" + "New balance: " + s.getBalance() + "$");
																	userbalance.setText("User Balance: " + s.getBalance() + "$"); //Show their balance
																}
																flag5 = true;
																break;
															}
														}
													}
													if(!flag4 && !flag5) {
														JOptionPane.showMessageDialog(null, "User: '" + sendto + "' does not have an account.");
													}
													break;
												}
											}
										}
									}
									else
										JOptionPane.showMessageDialog(null, "Amount must be a number");
								}
							}
						});
							
						layout.show(deck, "second");
					}
					else //If you didn't find the user
						JOptionPane.showMessageDialog(null, "User: '" + name1 + "' does not have an account.");
				}
			}
		});
		
		layout.show(deck, "first");
		
		this.add(deck);
		this.setSize(600, 220);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Bank");
		this.setVisible(true);
	}
	
	public static boolean isNumeric(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
	}
}
