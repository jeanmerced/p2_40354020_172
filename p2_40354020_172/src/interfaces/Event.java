package interfaces;
	
	/**
	 * @author jeanmerced
	 */

public interface Event {
	/**
	 * Accessor method.
	 * @return the priority number.
	 */
	int getPriority();
	
	/**
	 * Processes the current instance of event at time t.
	 * @param t time
	 */
	void process(int t);
	
}
