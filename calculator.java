import java.util.Scanner;

public class Calculator {
	static double add(double a, double b) {
		return a + b;
	}
	static double subtract(double a, double b) {
		return a - b;
	}
	static double multiply(double a, double b) {
		return a * b;
	}
	static double divide(double a, double b) {
		if(b==0) {
			System.out.println("Undefined");
		    return 0;
		}
		return a / b;
	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		char choice = ' ';
		
		do {
			System.out.println("\n--- Simple Calculator ---");
			System.out.print("Enter first number: ");
			double num1 = sc.nextDouble();
			
			System.out.print("Enter an Operator (+, -, *, /): ");
			char operator = sc.next().charAt(0);
			
			System.out.print("Enter second number: ");
			double num2 = sc.nextDouble();
			
			double result;
			
			switch (operator) {
			case '+':
				result = add(num1, num2);
				break;
			case '-':
				result = subtract(num1, num2);
				break;
			case '*':
				result = multiply(num1, num2);
				break;
			case '/':
				result = divide(num1, num2);
				break;
			default:
				System.out.println("Invalid operator!");
				continue;
			
			}
			  System.out.println("Result: " + result);
			  
			  System.out.println("Do you want to perform another calculation? (y/n): ");
			  choice = sc.next().charAt(0);
			  
		} while (choice == 'y'  || choice == 'Y' );
		
		System.out.println("Calculator Closed.");
		sc.close();

	}

}