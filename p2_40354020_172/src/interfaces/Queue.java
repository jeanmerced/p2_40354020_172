package interfaces;
	
	/**
	 * @author jeanmerced
	 */

public interface Queue<E> {
	
	/** Accessor Method.
	  	@return the size of the current instance.
	**/
	int size();

	/** Accessor Method.
		@return true if the current instance is empty; false, if not.  
	**/
	boolean isEmpty();

	/** Accessor Method.
		Accesses element in the current instance of the queue.
		The affected element is the one that has been in the queue
		for the longest time among all its current elements.
		@return reference to the element being accessed, null if queue is empty. 
	**/
	E first();

	/** Mutator Method.
		Adds a new element to the queue. 
		@param elemnt element to add 
	**/
	void enqueue(E element);

	/** Mutator Method.
		Similar to the first() method, but this time, the queue is
		altered since the accessed element is also removed from
		the queue.  
		@return reference to the element being removed, null if queue is empty. 
	**/
	E dequeue();

}
