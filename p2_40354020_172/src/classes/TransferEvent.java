package classes;

import java.util.ArrayList;
import java.util.Deque;

import interfaces.Event;
import interfaces.WaitingServingPolicy;

	/** 
	 * @author jeanmerced
	 */

public class TransferEvent implements Event {

	private ArrayList<Deque<Customer>> linesOfService;	// represents the lines of service for customers
	private WaitingServingPolicy policy;					// waiting/serving policy being applied
	private int priority;								// event priority number
	
	public TransferEvent(ArrayList<Deque<Customer>> ls, WaitingServingPolicy p) {
		linesOfService = ls;
		policy = p;
		priority = 2;
	}
	
	public int getPriority() {
		return priority;
	}

	public void process(int t) {
		if(policy.transferable()) {						// if the current waiting/serving policy
			Monitor m = new Monitor(linesOfService);		// is transferable, then a monitor is created
			m.transfer();								// and a transfer is done if necessary
		}
	}
}
