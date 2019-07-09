package capg.ui;

import java.util.Scanner;

public class MyUI {
	public static void main(String args[]) {
		int ch;
		char choice;
		MyModules mmo=MyModules();
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n *********************\n 1. Create Account \n 2. Show Balance \n 3. Deposit \n 4. Withdraw \n 5. Fund Transfer \n 6. Print Transactions \n 7. Exit\n*********************");
			System.out.print("Enter your choice : ");
			ch = sc.nextInt();
			switch(ch) {
			case 1:
				mmo.createAccount();
				break;
			case 2:
				mmo.showBalance();
				break;
			case 3:
				mmo.deposit();
				break;
			case 4:
				mmo.withdraw();
				break;
			case 5:
				mmo.fundTransfer();
				break;
			case 6:
				mmo.printTransactions();
				break;
			case 7:
				System.exit(0);
			}
			} while(ch != 7 );
		sc.close();
	}
		

}
