package interfaces;
	
	/**
	 * @author jeanmerced
	 */

public interface WaitingServingPolicy {

	/** Accessor Method.
  		@return the number of servers of the current instance.
	 **/
	int numberOfServers();
	
	/** Accessor Method.
  		@return the number of lines of the current instance.
	 **/
	int numberOfLines();
	
	/** Accessor Method.
  		@return true if policy is transferable; false, if not.
	 **/
	boolean transferable();
	
	/** Accessor Method.
		@return true if policy's arrival is monitored; false, if not.
	 **/
	boolean monitoredArrival();

}
