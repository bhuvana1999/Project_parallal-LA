package cap.service;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import cap.bean.BankBean;
import cap.dao.BankDao;

public class BankService implements BankServiceInF{
	BankDao dao= new BankDao();

	Scanner sc = new Scanner(System.in);

	
	public void createAccount() {
		System.out.print("Enter Name: ");
		String name = nameCheck(sc.next());
		System.out.print("Enter Mobile No.: ");
		long mobNo = mobCheck(sc.nextLong());
		//long accNo = mobNo - 1234;
		Random r=new Random();
		long accNo=r.nextInt(1000000);
		System.out.print("Enter Balance: "); 
		float balance = amountCheck(sc.nextFloat());
		BankBean cao = new BankBean(accNo, name, mobNo, balance);
		System.out.println("Account created with Account Number: " +accNo);
		bankAccountCreate(cao);
	}

	public void showBalance() {
		System.out.print("Enter Account Number: ");
		long accNo = sc.nextLong();
		BankBean sbo = new BankBean(accNo);
		showBalanceSer(sbo);
	}

	public void deposit() {
		System.out.print("Enter Account Number: ");
		long accNo = sc.nextLong();
		System.out.print("Enter Deposit Amount: ");
		float depAmount = amountCheck(sc.nextFloat());
		BankBean daob = new BankBean(accNo, depAmount);
		depositSer(daob);
	}

	public void withdraw() {
		System.out.print("Enter Account Number: ");
		long accNo = sc.nextLong();
		System.out.print("Enter Withdraw Amount: ");
		float withdrawAmount = amountCheck(sc.nextFloat());
		BankBean wao = new BankBean(withdrawAmount, accNo);
		withdrawSer(wao);
	}

	public void fundTransfer() {
		System.out.println("Enter Source Account Number: ");
		long sourceAccNo = sc.nextLong();
		System.out.println("Enter Destination Account Number: ");
		long destAccNo = sc.nextLong();
		System.out.println("Enter Amount to transfer: ");
		float transferAmount = amountCheck(sc.nextFloat());
		BankBean fto = new BankBean(sourceAccNo, destAccNo, transferAmount);
		transferSer(fto);
	    String transactions = transferAmount+ " transferred from Account number " +sourceAccNo+ " to " +destAccNo;
	    fto = new BankBean(transactions);
	}

	public void printTransactions() {
		 System.out.println(Arrays.toString(BankBean.transactions));
	}
	
	



	//Checking amount
	public float amountCheck(float amount) {
		while(true) {
			if(amount<=0) {
				System.out.println("Amount should be greater than 0.");
				System.out.println("Enter again: ");
				amount = sc.nextInt();
			}
			else {
				return amount;
			}
		}
	}

	// METHOD TO CHECK THE NAME
	public String nameCheck(String name) {
		while(true) {
			if(Pattern.matches("([A-Z])*([a-z])*", name)){
				return name;
			}
			else {
				System.out.println("Name should only have alphabets.");
				System.out.println("Enter again: ");
				name = sc.next();
			}
		}
	}

	//	checking the length of mob.no
	public long mobCheck(long mob) { 
		while(true) {
			if(String.valueOf(mob).length() < 10 || String.valueOf(mob).length() >10 ) {
				System.out.println("Enter valid mobile number.");
				mob = sc.nextLong();
			}
			else {
				return mob;
			}
		}
	}

	
	public void bankAccountCreate(BankBean CreateAccountObj) {
		dao.addCustomer(CreateAccountObj);
	}

	public void showBalanceSer(BankBean ShowBalObj) {

		if(dao.hm().isEmpty()) {									// CHECKING IF HASH MAP IS EMPTY OR NOT
			System.out.println("Please create an account first.");
		}
		else {
			long a1=ShowBalObj.getAccNo();
			if(dao.hm().containsKey(a1)) {
				System.out.println("Your Account Balance is: " +dao.hm().get(a1).getBalance());			// FETCHING THE BALANCE FROM HASHMAP & PRINTING 
			}
			else {
				System.out.println("Account does not Exist.");
			}
		}
	}

	public void depositSer(BankBean DepObj) {

		if(dao.hm().isEmpty()) {
			System.out.println("Please create an account first.");
		}
		else {
		long a1=DepObj.getAccNo();
			if(dao.hm().containsKey(a1)) {
				float dep = DepObj.getDepAmount() + dao.hm().get(a1).getBalance();						// ADDING DEPOSIT AMOUNT TO BANK ACCOUNT
				dao.hm().get(a1).setBalance(dep);
				System.out.println("Deposited successfully.");
				System.out.println("Your account balance is: " +dao.hm().get(a1).getBalance());					// PRINTING THE BANK BALANCE
			}
			else {
				System.out.println("No such Account Exist.");
			}
		}
	}

	public void withdrawSer(BankBean WithdrawObj) {
		if(dao.hm().isEmpty()) {
			System.out.println("Please create an account first.");
		}
		else {
			long a1=WithdrawObj.getAccNo();
			if(WithdrawObj.getWithdrawAmount() > dao.hm().get(a1).getBalance()) {		// CHECKING IF WITHDRAW AMOUNT IS GREATER THAN BALANCE OR NOT
				System.out.println("Can't withdraw money. Account Balance is low.");
			}
			else {
				if(dao.hm().containsKey(a1)) {
					float dep = dao.hm().get(a1).getBalance() - WithdrawObj.getWithdrawAmount();			// DECREMENTING WITHDRAW AMOUNT FROM BANK ACCOUNT
					dao.hm().get(a1).setBalance(dep);
					System.out.println("Withdraw successful.");
					System.out.println("Your account balance is: " +dao.hm().get(a1).getBalance());				// PRINTING REMAINING BALANCE
				}
				else {
					System.out.println("No such Account Exist.");
				}
			}
		}
	}

	public void transferSer(BankBean FundTransObj) {
		if(dao.hm().isEmpty()) {
			System.out.println("Please create an account first.");
		}
		else {
			long a1=FundTransObj.getSourceAccNo();
			long a2=FundTransObj.getSourceAccNo();
			if(dao.hm().containsKey(a1)) {				                             // CHECKING IF SOURCE ACCOUNT EXIST
				if(dao.hm().containsKey(a2)){				                               // CHECKING IF DESTINATION ACCOUNT EXIST
					if(dao.hm().get(a2).getBalance() > FundTransObj.getTransferAmount()) {		// CHECKING IF TRANSFER AMOUNT IS GREATER THAN BALANCE OR NOT
						float transfer = FundTransObj.getTransferAmount();
						dao.hm().get(a1).setBalance(dao.hm().get(a1).getBalance() - transfer);		// DECREMENTING THE TRANSFER AMOUNT
						dao.hm().get(a2).setBalance(dao.hm().get(a2).getBalance() + transfer);			// ADDING THE TRANSFER AMOUNT
						System.out.println("Money Transferred Successfully.");
						System.out.println("Remaining balance in account number "+a1+" is: " +dao.hm().get(a2).getBalance());
					}
					else {
						System.out.println("Can't transfer money. Insufficient balance");
					}
				}
				else {
					System.out.println("Destination Account Not Exist.");
				}
			}
			else {
				System.out.println("Source Account Not Exist.");
			}
		}
	}

}
