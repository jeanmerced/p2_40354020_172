package p2MainClasses;

import java.io.File;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;

import classes.ArrivalEvent;
import classes.Customer;
import classes.EventComparator;
import classes.SLMS;
import classes.ServiceCompletedEvent;
import classes.ServiceStartsEvent;
import classes.TransferEvent;
import classes.MLMS;
import classes.MLMSBLL;
import classes.MLMSBWT;
import classes.SLLQueue;
import interfaces.Event;
import interfaces.Queue;
import interfaces.WaitingServingPolicy;

	/**
	 * @author jeanmerced
	 */

public class theSystem {
	
	private static Queue<Customer> inputQueue;					// queue for customers waiting to arrive
	private static WaitingServingPolicy policy;					// waiting/serving policy being applied
	private static ArrayList<Deque<Customer>> linesOfService;		// represents the lines of service for customers
	private static Customer[] customersBeingServed;				// array to store and keep track of customers being served	
	private static ArrayList<Customer> finishedCustomers;			// array list to store customers that completed service

	public static void main(String[] args) throws IOException {
	
		Scanner dataFiles = new Scanner(new File("inputFiles", "dataFiles.txt"));
		while(dataFiles.hasNextLine()) {
			
			Scanner customerTimes;
			String currentDataFile = dataFiles.nextLine();
			File output = new File("outputFiles", currentDataFile.split("\\.")[0] + "_OUT.txt");
			FileWriter fw = new FileWriter(output);
			
			try {
				customerTimes = new Scanner(new File("inputFiles", currentDataFile));

			}
			catch(FileNotFoundException e) {
				fw.write("Input file not found");
				fw.close();
				continue;
			}
			
			int policyNumber = 1;
			while(policyNumber <= 12) {		// choose policy and number of servers
				
				switch(policyNumber) {
					case 1: 
						policy = new SLMS(1);
						break;
					case 2: 
						policy = new SLMS(3);
						break;
					case 3: 
						policy = new SLMS(5);
						break;
					case 4: 
						policy = new MLMS(1);
						break;
					case 5: 
						policy = new MLMS(3);
						break;
					case 6: 
						policy = new MLMS(5);
						break;
					case 7: 
						policy = new MLMSBLL(1);
						break;
					case 8: 
						policy = new MLMSBLL(3);
						break;
					case 9: 
						policy = new MLMSBLL(5);
						break;
					case 10: 
						policy = new MLMSBWT(1);
						break;
					case 11: 
						policy = new MLMSBWT(3);
						break;
					case 12: 
						policy = new MLMSBWT(5);
						break;
					default: 
						policy = new SLMS();
						break;
				}
				
				try {
					// read input data 
					customerTimes = new Scanner(new File("inputFiles", currentDataFile));
					inputQueue = new SLLQueue<>();
					int pid = 1;
					if(customerTimes.hasNext() == false) {
						customerTimes.close();
						throw new NoSuchElementException();
					}
					// fill queue with relevant data for each customer
					while(customerTimes.hasNext()) {
						Customer newCustomer = new Customer(pid, customerTimes.nextInt(), customerTimes.nextInt());
						inputQueue.enqueue(newCustomer);
						pid++;
					}
					// for writing file just once per data file 
					if(policyNumber == 1)
						fw.write("Number of customers is: " + currentDataFile.split("\\.")[0].substring(5) + "\n");
				}
				catch(NoSuchElementException e) {
					fw.write("Input file does not meet the expected format or it is empty.");
					break;
				}
				policyNumber++;
				
				// Initialize data Structures
				initializeDataStructures();
				
				// Create events and store in Priority Queue
				Event sce = new ServiceCompletedEvent(customersBeingServed, finishedCustomers);
				Event te = new TransferEvent(linesOfService, policy);
				Event sse = new ServiceStartsEvent(customersBeingServed, linesOfService);
				Event ae = new ArrivalEvent(customersBeingServed, linesOfService, policy, inputQueue);
				
				PriorityQueue<Event> events = new PriorityQueue<>(4, new EventComparator());
				events.add(sce);
				events.add(te);
				events.add(sse);
				events.add(ae);
				
				// Set time
				int t = 0;
				
				// Complete tasks
				boolean emptyServers = false;
				while(!(inputQueue.isEmpty()) || !emptyServers) {
					for(int i = 0; i < customersBeingServed.length; i++) 	// for each server
						if(customersBeingServed[i] != null)				// if customer is being served
							customersBeingServed[i].isServed(1);			// serve for 1 unit of time
					for(Event e : events) 
						e.process(t);
					// checks if servers are empty
					emptyServers = true;
						for(int i = 0; i < customersBeingServed.length; i++) 
							if(customersBeingServed[i] != null) 
								emptyServers = false;	
					t++;	
				}
				
				// Compute final statistics
				int t1 = t;
				double t2 = computeAverageWaitingTimePerCustomer();
				double m = computeAverageNumberOfOverpassingCustomers();
				fw.write(policy + ":  " + t1 + "  " + Math.round(t2*100.0)/100.0 + "  " + Math.round(m*100.0)/100.0 + "\n");
				
			}
			customerTimes.close();
			fw.close();
		}
		dataFiles.close();
	}
	
	private static void initializeDataStructures() {
		linesOfService = new ArrayList<>();
		for(int i = 0; i < policy.numberOfLines(); i++) {
			linesOfService.add(new ArrayDeque<Customer>());
		}
		customersBeingServed = new Customer[policy.numberOfServers()];
		finishedCustomers = new ArrayList<>();
	}
	
	private static double computeAverageWaitingTimePerCustomer() {
		double averageWaitingTimePerCustomer = 0;
		for(Customer c : finishedCustomers) {
			int waitingTimePerCustomer = c.getStartTime() - c.getArrivalTime();
			averageWaitingTimePerCustomer += waitingTimePerCustomer;
		}
		return averageWaitingTimePerCustomer / finishedCustomers.size();
	}
	
	private static double computeAverageNumberOfOverpassingCustomers() {
		double averageNumberOfOverpassingCustomers = 0;
		for(Customer c1 : finishedCustomers) 
			for(Customer c2 : finishedCustomers)
				if(c1.getArrivalTime() < c2.getArrivalTime() && c1.getStartTime() > c2.getStartTime())
					averageNumberOfOverpassingCustomers++;
		return averageNumberOfOverpassingCustomers / finishedCustomers.size();
	}
}



