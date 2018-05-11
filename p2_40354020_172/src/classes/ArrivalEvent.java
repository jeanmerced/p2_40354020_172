package classes;

import java.util.ArrayList;
import java.util.Deque;

import interfaces.Event;
import interfaces.Queue;
import interfaces.WaitingServingPolicy;

	/** 
	 * @author jeanmerced
	 */

public class ArrivalEvent implements Event {

	private Customer[] customersBeingServed;				// array to store and keep track of customers being served
	private ArrayList<Deque<Customer>> linesOfService;	// represents the lines of service for customers
	private WaitingServingPolicy policy;					// waiting/serving policy being applied
	private Queue<Customer> inputQueue;					// queue for customers waiting to arrive
	private int priority;								// event priority number
	
	public ArrivalEvent(Customer[] cbs, ArrayList<Deque<Customer>> ls, WaitingServingPolicy p, Queue<Customer> iq) {
		customersBeingServed = cbs;
		linesOfService = ls;
		policy = p;
		inputQueue = iq;
		priority = 4;
	}
	
	public int getPriority() {
		return priority;
	}

	public void process(int t) {
		if(!inputQueue.isEmpty()) {
			// if the waiting customer's arrival time == t
			if(inputQueue.first().getArrivalTime() == t) {
				// and if the policy requires the arrival to be monitored
				if(policy.monitoredArrival()) {
					Monitor m = new Monitor(customersBeingServed, linesOfService);
					m.arrival(inputQueue.dequeue());
				} 
				else {
					// policy does not require the arrival to be monitored
					Deque<Customer> minLine = linesOfService.get(0);			// set the first line to be the shortest
					int minLineSize = minLine.size();						
					for(int i = 1; i < linesOfService.size(); i++) 
						if(linesOfService.get(i).size() < minLineSize) {		// if a shorter line is found, new line
							minLine = linesOfService.get(i);					// is min line
							minLineSize = minLine.size();
						}	
					minLine.addLast(inputQueue.dequeue());					// add new customer to min line
				}
				process(t); 	// try to make another arrival at the same time t
				
				// try to start service at time t if possible
				ServiceStartsEvent sse = new ServiceStartsEvent(customersBeingServed, linesOfService);
				sse.process(t);
			}
		}
	}
}
