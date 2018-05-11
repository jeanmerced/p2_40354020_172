package classes;

import java.util.ArrayList;
import java.util.Deque;
	
	/**
	 * @author jeanmerced
	 */

public class Monitor {
	
	private Customer[] customersBeingServed;				// array to store and keep track of customers being served
	private ArrayList<Deque<Customer>> linesOfService;	// represents the lines of service for customers
	
	public Monitor(ArrayList<Deque<Customer>> ls) {
		linesOfService = ls;
	}
	
	public Monitor(Customer[] cbs, ArrayList<Deque<Customer>> ls) {
		customersBeingServed = cbs;
		linesOfService = ls;
	}
	
	/**
	 * Makes a transfer if necessary to keep lines balanced.
	 */
	public void transfer() {
		// Set first line of service as min line and max line 
		Deque<Customer> minLine = linesOfService.get(0);
		int minLineSize = minLine.size();
		Deque<Customer> maxLine = linesOfService.get(0);
		int maxLineSize = maxLine.size();
		
		for(int i = 1; i < linesOfService.size(); i++) {
			// if new line is shorter than min line, then new line is min line
			if(linesOfService.get(i).size() < minLineSize) {
				minLine = linesOfService.get(i);
				minLineSize = minLine.size();
			}
			// if new line is longer than max line, then new line is max line
			if(linesOfService.get(i).size() > maxLineSize) {
				maxLine = linesOfService.get(i);
				maxLineSize = maxLine.size();
			}
		}	
		
		// if max line and min line size difference is more than 1 customer, transfer is necessary
		if(maxLineSize - minLineSize > 1) {
			int i = linesOfService.indexOf(maxLine);	// maxLine's index
			int j = 1;
			int n = linesOfService.size();
			boolean finished = false;
			while(!finished)	{
				// find first min line closest to the right of max line
				// move from max line to min line the last customer
				if(linesOfService.get((i + j) % n).size() == minLineSize) {
					linesOfService.get((i + j) % n).addLast(maxLine.removeLast());
					finished = true;
				}
				j++;
			}
			transfer();	// try to transfer again until balanced
		}
	}
	
	/**
	 * Makes arrival of a new customer at the line with shortest amount of waiting time.
	 * @param newC new customer to arrive
	 */
	public void arrival(Customer newC) {
		// Set first line of service as min line with min waiting time (including customer being served) 
		Deque<Customer> minLine = linesOfService.get(0);
		int minLineWaitingTime = 0;
		for(Customer c : minLine) {
			minLineWaitingTime += c.getRemainingTime();
		}
		if(customersBeingServed[0] != null)
			minLineWaitingTime += customersBeingServed[0].getRemainingTime();
		
		for(int i = 1; i < linesOfService.size(); i++) {
			int lineWaitingTime = 0;
			for(Customer c : linesOfService.get(i)) 
				lineWaitingTime += c.getRemainingTime();
			if(customersBeingServed[i] != null)
				lineWaitingTime += customersBeingServed[i].getRemainingTime();
			// if a line's waiting time is shorter than min line, then new line is min line
			if(lineWaitingTime < minLineWaitingTime) {
				minLineWaitingTime = lineWaitingTime;
				minLine = linesOfService.get(i);
			}
		}
		
		minLine.addLast(newC);	// add new customer to the line with shortest waiting time
	}
	
}
