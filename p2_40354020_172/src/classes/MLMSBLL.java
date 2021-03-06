package classes;

import interfaces.WaitingServingPolicy;

	/** 
	 * @author jeanmerced
	 */

public class MLMSBLL implements WaitingServingPolicy {

	private int numberOfServers;			// number of servers
	private int numberOfLines;			// number of lines
	private boolean transferable;		// true if policy's line are transferable; false otherwise
	private boolean monitoredArrival;	// true if policy's arrival is monitored; false otherwise
	
	public MLMSBLL() {
		this(1);			// Default number of servers is 1
	}
	
	public MLMSBLL(int numberOfServers) {
		this.numberOfServers = numberOfServers;
		this.numberOfLines = numberOfServers;
		transferable = true;
		monitoredArrival = false;
	}
	
	public int numberOfServers() {
		return numberOfServers;
	}
	
	public int numberOfLines() {
		return numberOfLines;
	}
	
	public boolean transferable() {
		return transferable;
	}

	public boolean monitoredArrival() {
		return monitoredArrival;
	}
	
	public String toString() {
		return "MLMSBLL " + numberOfServers;
	}

}
