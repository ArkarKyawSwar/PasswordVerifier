/*
 * Password Verification
 * 
 * This program determines whether the password
 * the user typed in is valid or not according 
 * to the password requirements shown in the console. 
 */
import java.util.*;

public class PasswordVerifier {
	//Declared constants because they are not changing throughout the program and to avoid redundant parameters
	public static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
	public static final String UPPER = LOWER.toUpperCase();
	public static final String DIGITS = "1234567890";
	public static final String SPECIAL = "@#$%^";
	
	public static void main(String[] args) {
		System.out.println("Welcome! This program will determine whether your password is valid or not.\n");
		
		Scanner console = new Scanner(System.in);
		String choice = getChoice(console);
		
		//Indefinite loop so that the program works until the user chooses to quit.
		while(!choice.equals("Q")) {  
			if(choice.equals("R")) {  
				displayPasswordRequirements();
			}else if(choice.equals("V")) {
				String password = getInput(console);
				testingProcess(password);
			}else {    	//Following code is to make the program robust.
				System.out.println("\nInvalid entry. Please try again.\n");
			}
			choice = getChoice(console);
		}
		System.out.println("Thank you for using this program. Goodbye.");
	}
	//This method shows the menu of the program.
	public static void displayMenu() {
		System.out.println("\tR for Password Requirements");
		System.out.println("\tV for Verifying a New Password");
		System.out.println("\tQ for Quitting the Program.");
	}
	//This method returns the choice entered by the user.
	public static String getChoice(Scanner console) {
		displayMenu();
		System.out.print("Enter your choice: ");
		String temp = console.next().toUpperCase();
		return temp;
	}
	//This method shows the password requirements to the user.
	public static void displayPasswordRequirements() {
		System.out.println("Your password must be:");
		System.out.println("\t- Be between 8 to 12 characters");
		System.out.println("\t- Contain at least one lowercase letter (a-z)");
		System.out.println("\t- Contain at least one uppercase letter (A-Z)");
		System.out.println("\t- Contain at least one numerical digit (0-9)");
		System.out.println("\t- Not begin or end with a numerical digit");
		System.out.println("\t- Not contain two consecutive numerical digits");
		System.out.println("\t- When considering the 5 special Characters @, #, $, %, ^:");
		System.out.println("\t\t- contain at least one special character");
		System.out.println("\t\t- not contain more than 3 special characters");
		System.out.println("\t\t- not have any single special character repeated in the password\n");
	}
	//This method asks for the desired password for the user. 
	public static String getInput(Scanner console) {
		System.out.print("Enter new password: ");
		String temp = console.next();
		return temp;
	}
	/*
	 * This method sees if the password met all the requirements and if the user 
	 * put in an invalid password, the method prints out the error message
	 * explaining why the password is invalid.
	 */
	public static void testingProcess(String password) {
		System.out.println();
		
		if(!lengthTest(password)) {
			System.out.println("Invalid - must be between 8 and 12 characters\n");
		}else if(!minLower(password)) {
			System.out.println("Invalid - must contain at least one lowercase letter\n");
		}else if(!minUpper(password)) {
			System.out.println("Invalid - must contain at least one uppercase letter\n");
		}else if(!minDigits(password)) {
			System.out.println("Invalid - must contain at least one numerical digit\n");
		}else if(!startEndDigits(password)) {
			System.out.println("Invalid - cannot begin or end with a numerical digit\n");
		}else if(!consecutiveDigits(password)) {
			System.out.println("Invalid - cannot contain consecutive numerical digits\n");
		}else if(!minSpecial(password)) {
			System.out.println("Invalid - must contain at least one special character\n");
		}else if(!maxSpecial(password)) {
			System.out.println("Invalid - cannot contain more than three special characters\n");
		}else if(repeatSpecial(password)) {
			System.out.println("Invalid -  cannot have repeated special characters\n");
		}else {
			System.out.println("Valid\n");
		}
	}
	//This method determines if the length of the password is in the given range(8-12).
	public static boolean lengthTest(String password) {
		return password.length() >= 8 && password.length() <= 12;
	}
	//This method determines if the password contains at least one lowercase letter.
	public static boolean minLower(String password) {
		int count = 0;
		for(int i = 0; i < password.length(); i++) {
			if(LOWER.indexOf(password.charAt(i)) != -1) {
				count++;
			}
		}
		return count > 0;
	}
	//This method determines if the password contains at least one uppercase letter.
	public static boolean minUpper(String password) {
		int count = 0;
		for(int i = 0; i < password.length(); i++) {
			if(UPPER.indexOf(password.charAt(i)) != -1) {
				count++;
			}
		}
		return count > 0;
	}
	//This method determines if the password contains at least one digit.
	public static boolean minDigits(String password) {
		int count = 0;
		for(int i = 0; i < password.length(); i++) {
			if(DIGITS.indexOf(password.charAt(i)) != -1) {
				count++;
			}
		}
		return count > 0;
	}
	//This method determines if the password contains at least one special character.
	public static boolean minSpecial(String password) {
		int count = 0;
		for(int i = 0; i < password.length(); i++) {
			if(SPECIAL.indexOf(password.charAt(i)) != -1) {
				count++;
			}
		}
		return count > 0;
	}
	//This method determines if the password starts and/or ends with a digit.
	public static boolean startEndDigits(String password) {
		return DIGITS.indexOf(password.charAt(0)) == -1 && DIGITS.indexOf(password.charAt(password.length()-1)) == -1;
	}
	//This method determines if the password contains consecutive digits.
	public static boolean consecutiveDigits(String password) {
		int count = 0;
		for(int i = 0; i < password.length() - 1; i++) {	
			if(DIGITS.indexOf(password.charAt(i)) != -1 && DIGITS.indexOf(password.charAt(i+1)) != -1) {
				count++;
			}
		}
		return count == 0;
	}
	//This method determines if the password contains more than 3 special characters.
	public static boolean maxSpecial(String password) {
		int count = 0;
		for(int i = 0; i < password.length(); i++) {
			if(SPECIAL.indexOf(password.charAt(i)) != -1) {
				count++;
			}
		}
		return count <= 3;
	}
	//This method returns true if any special characters are repeated in the password.
	public static boolean repeatSpecial(String password) {
		return !repeatSpecial(password, '@') || !repeatSpecial(password, '#') ||
				!repeatSpecial(password, '$') || !repeatSpecial(password, '%') ||
				!repeatSpecial(password, '^');
	}
	/*
	 * This method determines if any of the special characters appear more than 
	 * once in the password, using the parameter sc(which is meant for each special character).
	 */
	public static boolean repeatSpecial(String password, char sc) {
		int count = 0;
		for(int i = 0; i < password.length(); i++) {
			if(password.charAt(i) == sc) {
				count++;
			}
		}
		return count <= 1;
	}
}

