package classes;

import java.util.ArrayList;

import interfaces.Event;

	/**
	 * @author jeanmerced
	 */

public class ServiceCompletedEvent implements Event {
	
	private Customer[] customersBeingServed;			// array to store and keep track of customers being served	
	private ArrayList<Customer> finishedCustomers;	// array list to store customers that completed service
	private int priority;							// event priority number
	
	public ServiceCompletedEvent(Customer[] cbs, ArrayList<Customer> fc) {
		customersBeingServed = cbs;
		finishedCustomers = fc;
		priority = 1;
	}

	public int getPriority() {
		return priority;
	}

	public void process(int t) {
		for(int i = 0; i < customersBeingServed.length; i++) {
			if(customersBeingServed[i] != null)							// if a line's server is occupied
				if(customersBeingServed[i].getRemainingTime() == 0) {		// and if that customer is finished
					finishedCustomers.add(customersBeingServed[i]);		// then that customer is added to 
					customersBeingServed[i] = null;						// the finishedCustomers list and
				}														// that line's server is now free
		}
	}
}
