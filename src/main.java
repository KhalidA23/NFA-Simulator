import java.util.ArrayList;
import java.util.Scanner;

public class main {
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("[1]NFA Problem");
			System.out.println("[2]Exit");
			System.out.print("> ");
			choice = input.nextInt();
			
			if (choice != 1 && choice != 2)
				System.out.println("Wrong Option!");
			
			else if (choice == 1) {
				char alphabet;
				int num;
				int start;
				int Final;
				int transition = 0;
				
				System.out.println("Enter Number Of States: ");
				System.out.print("> ");
				num = input.nextInt();
				NFA problem = new NFA(num);
				
				do {
					System.out.println("Enter Alphabet: (# when finish) ");
					System.out.print("> ");
					alphabet = input.next().charAt(0);
					if (alphabet != '#')
						problem.addAlphabet(alphabet);
				} while (alphabet != '#');				

				do {
					System.out.println("Enter Number Of Start State: (-1 when finish) ");
					System.out.print("> ");
					start = input.nextInt();
					if (start > problem.numberOfStates)
						System.out.println("Number Of State Is = " + problem.numberOfStates + ", But you entered " + start);
					else if (start < 0 && start != -1)
						System.out.println("State Number Must Be > 0");
					else if (problem.startStates.size() == 0 && start == -1)
						System.out.println("Must Have At Least 1 Start State");
					else if (start != -1)
						problem.addStartState(start);
				} while (start != -1);				
				
				do {
					System.out.println("Enter Number Of Final State: (-1 when finish) ");
					System.out.print("> ");
					Final = input.nextInt();
					if (Final > problem.numberOfStates)
						System.out.println("Number Of State Is = " + problem.numberOfStates + ", But you entered " + Final);
					else if (Final < 0 && Final != -1)
						System.out.println("State Number Must Be > 0");
					else if (problem.finalStates.size() == 0 && Final == -1)
						System.out.println("Must Have At Least 1 Final State");
					else if (Final != -1)
						problem.setFinalState(Final);
				} while (Final != -1);	
				
				System.out.println("Enter Transition");
				int from;
				int to;
				char symbol;
				
				do {
					System.out.println("\n-------------------------------");
					System.out.print("From State Number: ");
					from = input.nextInt();
					System.out.print("Symbol: ");
					symbol = input.next().charAt(0);
					System.out.print("To State Number: ");
					to = input.nextInt();
					System.out.println("-------------------------------");
					
					if (symbol != '#')
						problem.states.get(from-1).addTransition(symbol, to);
					else
						problem.states.get(from-1).addLambdaTransition(to);
					
					System.out.println("\nAnother Transition? (-1 if no, any number if yes)");
					System.out.print("> ");
					transition = input.nextInt();
					
				} while (transition != -1);
				
				
				int continuou = 0;
				do {
					
					System.out.print("\nEnter String Input: ");
					String s = input.next();
				
					problem.processInput(s);
										
					System.out.println("\nAnother Input? (-1 if no, any number if yes)");
					System.out.print("> ");
					continuou = input.nextInt();
					
				} while (continuou != -1);
			}			
		} while (choice != 2);
	}
}
