/**
 * @author J.Ismail
 *
 */

import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;


public class BankAccount {

	private static int MAX_PASSWORD_LENGTH = 5;
	
	private static int MAX_ACCT_LENGTH = 5000;
	
	/* Members, DATA members */
	private int acctNum;
	private double balance;
	private String fName;
	private String lName;
	private String pswd;
	private String log;
	
	
	/**
	 * Default Constructor
	 */
	public BankAccount(){
		acctNum = genAcctNum(MAX_ACCT_LENGTH);
		balance = 0.0;
		fName = null;
		lName = null;
		pswd = genPswd(MAX_PASSWORD_LENGTH); // use method genPswd
		log = "";
		
	}
	
	/**
	 * 
	 * @param s1 - First Name
	 * @param s2 - Last Name
	 */
	public BankAccount (String s1, String s2){
		
		//this();
		acctNum = genAcctNum(MAX_ACCT_LENGTH);
		balance = 0.0;
		this.fName = s1;
		this.lName = s2;
		pswd = genPswd(MAX_PASSWORD_LENGTH); // use method genPswd
		log = "";
	}
	
	/**
	 * 
	 * @param acct
	 * @param balance
	 * @param fName
	 * @param lName
	 * @param pswd
	 * @param log
	 */
	BankAccount (int acct, double balance, String fName, String lName, String pswd, String log){
		//this();
		this.acctNum = acct;
		this.balance = balance;
		this.fName = fName;
		this.lName = lName;
		this.pswd = pswd; 
		this.log = log;
		
	}
	
	
	
	/**
	 * deposit amount
	 * @param amount
	 * @return true if deposit is successful, false otherwise
	 */
	public boolean deposit (double amount) {
		boolean b = false;
		// check if amount is positive
		
		if( amount > 0) {
			balance = amount + balance;
			log = log + "\n" + genTimestamp()  + " Deposit Successfull [$"+amount+"]";
			b = true;
		}else {
			log = log + "\n" + genTimestamp()  + " Deposit Failed [$"+amount+"]";
			b = false;
		}
		
		return b;
	}
	
	/**
	 * withdraw amount
	 * @param amount
	 * @return true if withdrawal is successful, false otherwise
	 */
	public boolean withdraw (double amount) {
		boolean b = false;
		// some action to do...
		
		if(amount > 0 && amount < balance) {
			balance = balance - amount;
			log = log + "\n" + genTimestamp()  + " Withdraw Successfull [$"+amount+"]";
			b = true;
		}else { 
			log = log + "\n" + genTimestamp()  + " Withdraw failed [$"+amount+"]";
			b = false;
		}
		
		return b;
	}
	
	// needed to update log
	public void updateLog(String s) {
		log = log + s;
	}
	
	// amount - transfer amount, the amount of money
	// bank - target bank where money is to be deposited
	/**
	 * TransferTo
	 * @param amount
	 * @param bank
	 * @return true if the calling account has enough money for the transfer, false otherwise
	 */
	boolean transferTo (double amount, BankAccount bank) {
		boolean b = false;
		// some action to do...
		if(amount > 0 && amount < balance) {
			balance = balance - amount;
			log = log + "\n" + genTimestamp()  + " Transfer [$" + amount + " to account " + bank.getAcctNum() + "]";
			bank.updateLog("\n" + genTimestamp()  + " Transfer [$" + amount + " received from + " + acctNum + "]");
			b = true;
			bank.deposit(amount);
		}else { 
			b = false;
			log = log + "\n" + genTimestamp()  + " Transfer Failed [$" + amount + " to account " + bank.getAcctNum() + "]";
		}
		
		return b;
	}
	
	/**
	 * 
	 * @param s String password to check
	 * @return true if the parameter matches exactly with the member pswd, false otherwise
	 */
	boolean checkPswd (String s) {
		if (s.equals(pswd))
			return true;
		else return false; 	
		
		
	}
	
	// s current password, s2 new password
	/**
	 * 
	 * @param s current Password as String
	 * @param s2 new Password as String
	 * @return true if current password matches exactly with the member pswd, false otherwise
	 */
	boolean resetPswd (String s, String s2) {
		boolean b = false;
		// some action to do...
		if(checkPswd(s)) {
			System.out.println("Password correct");
			log = log + "\n" + genTimestamp()  + " Password Successfully Change";
			b = true;
			pswd = s2;
		}else {
			System.out.println("Incorrect password");
			log = log + "\n" + genTimestamp()  + " Reset Password Failed!";
			b = false;
		}
		
		
		return b;
	}
	
	// reset Account Number
	void resetAcctNum() {
		// use the method genAcctNum
		acctNum = genAcctNum(MAX_ACCT_LENGTH);
	}
	
	/**
	 * 
	 * @param s set First Name String
	 */
	void setFName (String s) {
		this.fName = s;
	}
	
	/**
	 * 
	 * @param s String set Last Name
	 */
	void setLName (String s) {
		this.lName = s;
	}
	
	
	
	/**
	 * @return the acctNum
	 */
	public int getAcctNum() {
		return acctNum;
	}


	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}


	/**
	 * @return the fName
	 */
	public String getFName() {
		return fName;
	}


	/**
	 * @return the lName
	 */
	public String getLName() {
		return lName;
	}

	/**
	 * @return the log
	 */
	public String getLog() {
		return log;
	}

	/**
	 * 
	 * @return String password
	 */
	public String getPassword() {
		return pswd;
	}
	
	
	private int genAcctNum(int i) {
		// update code....
		Random rand = new Random();
		int iacctnum = rand.nextInt(i) + 1;
		return iacctnum;
	}
	
	private String genPswd (int length) {
		String[] strArray = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z", "A",
				"B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S",
				"T", "U", "V", "W", "X", "Y", "Z", "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "0" };
		
		String temps = "";
		// create instance of Random class
		Random ran = new Random();
		// generate random integers, using a for loop from 0 to int length provided
		for(int i = 0 ; i < length; i++) {
			int r = ran.nextInt(strArray.length-1); // pass the strArray length - 1 = which is the limit, eg: 64
			//System.out.println("str array: " + strArray[r]);
			temps += strArray[r]; // the random number is then used to grab a random character from the array, and attach to the string
			//System.out.println(temps);
		}
		//int r1 = ran.nextInt(62);
		//System.out.println("random: " + r1);
		
		//System.out.println(temps);
		
		// return the password string		
		return temps;
	}
	
	// generate a TimeStamp
	private String genTimestamp() {
		// update code ...
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		return timeStamp;
	}
	
	// display the info
	 void display() {
		// do something ...
		System.out.println("Acct Num: " + acctNum);
		System.out.println("Balance: " + balance);
		System.out.println("First Name: " + fName);
		System.out.println("Last Name: " + lName);
		System.out.println("Password: " + pswd);
		System.out.println("LOG: \n" + log);
	}
	
	 

	@Override
	public String toString() {
		return "BankAccount [acctNum=" + acctNum + ", balance=" + balance + ", fName=" + fName + ", lName=" + lName
				+ ", pswd=" + pswd + ", log=" + log + ", genTimestamp()=" + genTimestamp() + "]";
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// (int acct, double balance, String fName, String lName, String pswd, String log)
		BankAccount bank = new BankAccount(101, 10, "umar", "ismail", "123", "log.txt");
		bank.deposit(100);
		if(bank.withdraw(50)) {
			System.out.println("Success withdraw");
		}else {
			System.out.println("Fail withdraw");
		}
		//System.out.println(bank);
		bank.display();
		//check password
//		String check = bank.getPassword();
//		System.out.println(check);
//		String wrongpass = "123a";
//		if(bank.checkPswd(wrongpass)) {
//			System.out.println("Password correct");
//		}else {
//			System.out.println("Incorrect password");
//		}
		
		// rest password
		String newpassword = "Java1973";
		if(bank.resetPswd("541lop", newpassword)) {
			System.out.println("success , password changed to: " + newpassword);
		}else {
			System.out.println("Failed to update password");
		}

		
		// reset the account number
//		bank.resetAcctNum();
//		System.out.println(bank);
		
		
		BankAccount bank2 = new BankAccount(300, 100, "Jahangir", "ismail", "123", "log.txt");
		bank2.deposit(5000);
		//System.out.println(bank2);
		
	   // do a transfer
		if(bank.transferTo(100, bank2)) {
			System.out.println("Success transfer");
			System.out.println("Bank 1: " + bank.getBalance());
			System.out.println("Bank 2: " + bank2.getBalance());
		}else {
			System.out.println("Failed to transfer");
		}
		
		
		if(bank2.transferTo(2000, bank)) {
			System.out.println("Success transfer");
			System.out.println("Bank 1: " + bank.getBalance());
			System.out.println("Bank 2: " + bank2.getBalance());
		}else {
			System.out.println("Failed to transfer");
		}
		
		System.out.println("\ndisplay bank1\n");
		bank.display();
		System.out.println("\ndisplay bank2\n");
		bank2.display();
	}

}
