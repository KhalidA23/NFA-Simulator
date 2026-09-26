import java.util.ArrayList;

public class NFA {
	int numberOfStates;
	ArrayList<Character> alphapet;
	ArrayList<State> states;
	ArrayList<State> startStates;
	ArrayList<State> finalStates;
	
	public NFA (int number){
		numberOfStates = number;
		alphapet = new ArrayList<>();
        states = new ArrayList<>();
        startStates = new ArrayList<>();
        finalStates = new ArrayList<>();
        
		for(int i = 0; i < numberOfStates; i++) {
			State s = new State(false, i+1);
			states.add(s);
		}
	}
	
	public void addAlphabet (char s) {
		alphapet.add(s);
	}
	
	public void addStartState(int number) {
		startStates.add(states.get(number-1));
	}
	
	public void setFinalState(int number) {
		states.get(number - 1).isFinal = true;
		finalStates.add(states.get(number-1));
	}
	
	public void addTransition(int fromState, char symbol, int toState) {
		if (symbol != '#')
			states.get(fromState - 1).addTransition(symbol, toState);
		else
			states.get(fromState - 1).addLambdaTransition(toState);
	}
	
	public void LambdaTransitionCheck (ArrayList<State> current) {
		for (int i = 0; i < current.size(); i++) {
			State currentState = current.get(i); 
			if (currentState != null) {
				for (int j = 0; j < currentState.lambdaTransitions.size(); j++) {
					int toState = currentState.lambdaTransitions.get(j).toState;
					State s = states.get(toState - 1);
					if (!current.contains(s)) {
						current.add(s);
					}
				}
			}
		}
	}
	
	public void processOneSymbol (State s, char symbol, ArrayList<State> nextStates) {
		for (int i = 0; i < s.symbolTransitions.size(); i++) { 
			int stateNumber = s.symbolTransitions.get(i).toState;
			if (s.symbolTransitions.get(i).symbol == symbol)
				if (!nextStates.contains(states.get(stateNumber - 1)))
					nextStates.add(states.get(stateNumber - 1));
		}
	}
	
	public boolean processInput (String input) {
		ArrayList<State> currentStates = new ArrayList<State>();
		currentStates.addAll(startStates);
		LambdaTransitionCheck(currentStates);

		ArrayList<State> nextStates = null;
		
		System.out.println("-------------Processing Step-----------------");

		for (int i = 0; i < input.length(); i++) {
			System.out.println(currentStates.toString());
			System.out.println(input.charAt(i) + " >------------------------------");
			
			nextStates = new ArrayList<State>();
			for (int j = 0; j < currentStates.size(); j++) {
				State s = currentStates.get(j);
				processOneSymbol(s,input.charAt(i),nextStates);
			}
			currentStates = nextStates;
			LambdaTransitionCheck(currentStates);
		}
		System.out.println(currentStates.toString());
		System.out.println("------------------Finish---------------------");
		
		for (int i = 0; i < currentStates.size(); i++) {
			if (currentStates.get(i).isFinal == true) {
				System.out.println("Input Accepted!, State ("+ currentStates.get(i).id +") is final state");
				return true;
			}
		}
		System.out.println("Input Rejected!, There is not final state at last level");
		return false;
	}
}
