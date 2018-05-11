package classes;

	/**
	 * @author jeanmerced
	 */
public class Customer {


	private int pid;     		// id of this customer
	private int arrivalTime;		// arrival time of this customer
	private int startTime;  		// time when this customer starts receiving service
	private int remainingTime;  	// remaining service time for this customer
	
	public Customer(int id, int at, int rt) { 
		pid = id; 
		arrivalTime = at; 
		startTime = -1;
		remainingTime = rt;
	}
	
	public int getPid() {
		return pid;
	}
	   
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public int getRemainingTime() {
		return remainingTime;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public void setStartTime(int t) {
		startTime = t;
	}
	   
	/**
	 * Registers an update of serviced received by this customer. 
	 * @param q the time of the service being registered. 
	 */
	public void isServed(int q) { 	
		if (q < 0) return;   // only register positive value of q
			remainingTime -= q; 
	}
			
	/**
	 * Generates a string that describes this customer; useful for printing
	 * information about the customer.
	 */
	public String toString() { 	
		return	" PID = " + pid + 
				" Arrival Time = " + arrivalTime +
				" Start Time = " + startTime +
				" Remaining Time = " + remainingTime; 				
	}
}