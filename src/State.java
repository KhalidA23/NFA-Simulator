import java.util.ArrayList;

public class State {
	ArrayList<Transition> symbolTransitions;
	ArrayList<Transition> lambdaTransitions;
	int id;
	boolean isFinal;
	
	public State (boolean Final, int i) {
		isFinal = Final;
		id = i;
		symbolTransitions = new ArrayList<>();
		lambdaTransitions = new ArrayList<>();
	}
	
	public void addLambdaTransition (int state) {
		Transition t = new Transition (state,'#');
		lambdaTransitions.add(t);
	}
	
	public void addTransition (char symbol, int state) {
		Transition t = new Transition (state, symbol);
		symbolTransitions.add(t);
	}
	
	public String toString() {
		return ""+id;
	}
}
