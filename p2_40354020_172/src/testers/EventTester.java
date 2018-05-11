package testers;

import java.util.ArrayDeque;

import java.util.ArrayList;
import java.util.Deque;

import classes.ArrivalEvent;
import classes.Customer;
import classes.MLMS;
import classes.MLMSBLL;
import classes.MLMSBWT;
import classes.SLLQueue;
import classes.SLMS;
import classes.ServiceCompletedEvent;
import classes.ServiceStartsEvent;
import classes.TransferEvent;
import interfaces.Event;
import interfaces.Queue;
import interfaces.WaitingServingPolicy;

	/**
	 * @author jeanmerced
	 */

public class EventTester {
	
	public static void main(String[] args) {
		
		Queue<Customer> inputQueue = new SLLQueue<>();
		Customer c;
		c = new Customer(0, 0, 1);
		inputQueue.enqueue(c);
		System.out.println(c);
		c = new Customer(1, 0, 2);
		inputQueue.enqueue(c);
		System.out.println(c);
		c = new Customer(2, 3, 2);
		inputQueue.enqueue(c);
		System.out.println(c);
		c = new Customer(3, 3, 3);
		inputQueue.enqueue(c);
		System.out.println(c);
		c = new Customer(4, 3, 6);
		inputQueue.enqueue(c);
		System.out.println(c);
		c = new Customer(5, 3, 3);
		inputQueue.enqueue(c);
		System.out.println(c);
		c = new Customer(6, 3, 4);
		inputQueue.enqueue(c);
		System.out.println(c);
		c = new Customer(7, 3, 1);
		inputQueue.enqueue(c);
		System.out.println(c);
		c = new Customer(8, 3, 1);
		inputQueue.enqueue(c);
		System.out.println(c);
		c = new Customer(9, 4, 2);
		inputQueue.enqueue(c);
		System.out.println(c);
		c = new Customer(10, 4, 1);
		inputQueue.enqueue(c);
		System.out.println(c);
		c = new Customer(11, 4, 5);
		inputQueue.enqueue(c);
		System.out.println(c);
		c = new Customer(12, 4, 7);
		inputQueue.enqueue(c);
		System.out.println(c);
		
		
		ArrayList<Deque<Customer>> linesOfService = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			linesOfService.add(new ArrayDeque<Customer>());
		}
		
		ArrayList<Customer> finishedCustomers = new ArrayList<>();
		
		WaitingServingPolicy policy;
		
		/**************************/
		//policy = new SLMS(3);
		policy = new MLMS(3);
		//policy = new MLMSBLL(3);
		//policy = new MLMSBWT(3);
		
		Customer[] customersBeingServed = new Customer[policy.numberOfServers()];
		
		Event sce = new ServiceCompletedEvent(customersBeingServed, finishedCustomers);
		Event te = new TransferEvent(linesOfService, policy);
		Event sse = new ServiceStartsEvent(customersBeingServed, linesOfService);
		Event ae = new ArrivalEvent(customersBeingServed, linesOfService, policy, inputQueue);
		
		boolean emptyServers = false;
		int t = 0;
		while(!inputQueue.isEmpty() || !emptyServers) {
			System.out.println("\nLoop with t = " + t);
			sce.process(t);
			System.out.println("\nLines after service completed event: ");
			printLinesOfService(linesOfService);
			te.process(t);
			System.out.println("\nLines after tranfer event: ");
			printLinesOfService(linesOfService);
			sse.process(t);
			System.out.println("\nLines after service starts event: ");
			printLinesOfService(linesOfService);
			ae.process(t);
			System.out.println("\nLines after arrival event: ");
			printLinesOfService(linesOfService);
			System.out.println("");
			
			emptyServers = true;
			for(int i = 0; i < customersBeingServed.length; i++)
				if(customersBeingServed[i] != null) {
					customersBeingServed[i].isServed(1);
					emptyServers = false;
				}
			t++;
		}		
	}
	
	private static void printLinesOfService(ArrayList<Deque<Customer>> ls) {
		for(int i = 0; i < ls.size(); i++) {
			System.out.print("Line " + i + " = " + ls.get(i).size() + "  ");
		}
	}
}
