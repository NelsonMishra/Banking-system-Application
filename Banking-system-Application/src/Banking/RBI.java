package Banking;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class RBI {
	public static void main(String[] args) {
		System.out.println("******************code by NELSHAN MANDAN[nelsonmishra]*********************");
		System.out.println("\n");
		
		System.out.println("******************Welcome To National Banking System*********************");
		System.out.println("\n");
		System.out.println("Doy you want to Open Account: 1. Yes. 2. No");

		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();

		if (choice.equalsIgnoreCase("Yes")) {
			OpenAccount oa = new OpenAccount();
			oa.createAccount();

		}
		if (choice.equalsIgnoreCase("No")) {
			BankAccount ba = new BankAccount();
			ba.showMenu();
		}
	}

}

class OpenAccount {
	String name;
	int accountnum;
	String accountType;
	String dob;
	String bank;

	void createAccount() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter in which bank what to open the Account: 1.SBI   2.BOI   3.ICICI");
		int choiceBank = sc.nextInt();

		if (choiceBank == 1) {
			bank = "SBI";
		}
		if (choiceBank == 2) {
			bank = "BOI";
		}
		if (choiceBank == 3) {
			bank = "ICICI";
		}

		System.out.println("Please enter your name: ");
		sc.nextLine();
		name = sc.nextLine();

		System.out.println("Please enter your DOB: ");
		dob = sc.nextLine();

		System.out.println("Enter your account Type , want to open: 1.Saving 2.Current");
		int choice = sc.nextInt();
		if (choice == 1) {
			accountType = "Saving";

		}
		if (choice == 2) {
			accountType = "Current";
		}

		System.out.println("YOur account has been opened with these Details: ");
		System.out.println("Bank:" + bank);
		System.out.println("Name:" + name);
		System.out.println("DOB:" + dob);
		System.out.println("Account Type:" + accountType);
		System.out.println("Account Number:" + Math.random());

		System.out.println("\n");

		BankAccount ba = new BankAccount();
		ba.showMenu();
		sc.close();

	}

}

class BankAccount {
	int balance;
	int previousTransaction;
	String customerName;
	String customerId;
	String accountType;
	double totalInterest;

	void calulateInterest(double balance) {
		System.out.println("Whats your account type : 1. Saving 2.Current");
		Scanner sc = new Scanner(System.in);

		int choice = sc.nextInt();

		if (choice == 1) {
			accountType = "Saving";
			int r = 5;
			int t;
			System.out.println("Enter yr. to calculate Interest:");
			t = sc.nextInt();
			double finalAmount = balance * (1 + r * t);
			totalInterest = finalAmount - balance;
			System.out.println("Total interest " + totalInterest);
		}
		if (choice == 2) {
			accountType = "Current";
			int r = 8;
			int t, n;
			System.out.println("Enter yr. to calculate Interest:");
			t = sc.nextInt();
			System.out.println("enter the frequency that interest has been compound in a year");
			n = sc.nextInt();
			double finalAmount = balance * (Math.pow((1 + r / n), n * t));
			totalInterest = finalAmount - balance;
			System.out.println("Total interest " + totalInterest);
			sc.close();
		}
	}

	void deposit(int amount) {
		if (amount != 0)
			;
		{
			balance = balance + amount;
			System.out.println("Balance after depostit:" + balance);
			previousTransaction = amount;

		}
	}

	void withdraw(int amount) {
		if (amount != 0)
			;
		{
			balance = balance - amount;
			System.out.println("Balance after Withdraw : " + balance);
			previousTransaction = -amount;

		}
	}

	void getPerviousTransaction() {

		FileOutputStream out;
		PrintStream p;

		try {
			out = new FileOutputStream("c:\\Users\\Downloads");
			p = new PrintStream(out);

			if (previousTransaction > 0) {
				p.append("Deposited:" + previousTransaction);

			} else if (previousTransaction < 0) {
				p.append("withdraw:" + Math.abs(previousTransaction));

			} else {
				System.out.println("No Transaction occured");
			}

			p.close();
		} catch (Exception e) {
			System.err.println("Error in printing the dara  " + e);
		}
	}

	void showMenu() {
		char option = '\0';
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to menu");
		System.out.println("\n");
		System.out.println("A. Check Balance");
		System.out.println("B. Deposite Amount");
		System.out.println("C. Withdraw Amount ");
		System.out.println("D. See Previous Transaction");
		System.out.println("E. Calculate Interest");
		System.out.println("F. Exit ");

		do {
			System.out.println("********************************************");
			System.out.println("Enter an option");
			option = scanner.next().charAt(0);
			System.out.println("\n");

			switch (option) {
			case 'A':
				System.out.println("-------------------------------");
				System.out.println("Balance = " + balance);
				System.out.println("\n");
				break;

			case 'B':
				System.out.println("-------------------------------");
				System.out.println("Enter an amount to deposite: ");
				int amount = scanner.nextInt();
				deposit(amount);
				System.out.println("\n");
				break;

			case 'C':
				System.out.println("-------------------------------");
				System.out.println("enter the amount to withdraw");
				int amount2 = scanner.nextInt();
				withdraw(amount2);
				System.out.println("\n");
				break;

			case 'D':
				System.out.println("-------------------------------");
				System.out.println("previousTransaction = " + previousTransaction);
				System.out.println("\n");
				break;

			case 'E':
				System.out.println("-------------------------------");
				System.out.println("Total interest = " + totalInterest);
				System.out.println("\n");
				break;

			case 'F':
				System.out.println("-------------------------------");
				System.out.println("You are logOut/ Thanks for your time ");
				System.out.println("\n");
				break;

			}

		} while (option != 0);

	}

}