package cap.ui;

import java.util.Scanner;

import cap.service.BankService;

public class BankMainUI {
	public static void main(String args[]) {
		int ch;
		char choice;
		BankService bso = new BankService();
		
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("*********************");
			System.out.println("1. Create Account \n 2. Show Balance \n 3. Deposit \n 4. Withdraw \n 5. Fund Transfer \n 6. Print Transactions \n 7. Exit");
			System.out.println("*********************");
			System.out.print("Enter your choice : ");
			ch = sc.nextInt();
			switch(ch) {
			case 1:
				bso.createAccount();
				break;
			case 2:
				bso.showBalance();
				break;
			case 3:
				bso.deposit();
				break;
			case 4:
				bso.withdraw();
				break;
			case 5:
				bso.fundTransfer();
				break;
			 case 6:
				bso.printTransactions();
				break;
			case 7:
				
				System.out.print("Do you want to Exit (yes or no)...? : ");
				choice = sc.next().charAt(0);
				if(choice == 'y' || choice=='Y')
				{
					System.out.println("Thank You...!");
				System.exit(0);
				}
					
				else {
					continue;
					
				     }
			}
			System.out.print("Do you want to continue (yes or no)...? : ");
			choice = sc.next().charAt(0);
			if(choice == 'y' || choice=='Y')
				continue;
			else {
				System.out.println("Thank You...!");
				System.exit(0);
			}
		} while(ch != 7 );
		sc.close();
	}
} 

