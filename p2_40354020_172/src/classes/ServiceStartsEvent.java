package classes;

import java.util.ArrayList;
import java.util.Deque;

import interfaces.Event;

	/** 
	 * @author jeanmerced
	 */

public class ServiceStartsEvent implements Event {
	
	private Customer[] customersBeingServed;				// array to store and keep track of customers being served
	private ArrayList<Deque<Customer>> linesOfService;	// represents the lines of service for customers
	private int priority;								// event priority number
	
	public ServiceStartsEvent(Customer[] cbs, ArrayList<Deque<Customer>> ls) {
		customersBeingServed = cbs;
		linesOfService = ls;
		priority = 3;
	}
	
	public int getPriority() {
		return priority;
	}

	public void process(int t) {		
		int i = 0;
		for(Deque<Customer> line : linesOfService) {		
			if(!line.isEmpty()) 	
				// for each non-empty line if a server is available
				if(customersBeingServed[i] == null && line.peekFirst().getStartTime() < 0) {
					line.peekFirst().setStartTime(t);			// set that customer's start time to that of t
					customersBeingServed[i] = line.pollFirst();	// and add to server's array
				}
			i++;
		}
	}
}
