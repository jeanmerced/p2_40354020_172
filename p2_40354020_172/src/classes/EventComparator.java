package classes;

import java.util.Comparator;

import interfaces.Event;

	/**
	 * @author jeanmerced
	 */

public class EventComparator implements Comparator<Event> {

	public int compare(Event e1, Event e2) {
		if(e1.getPriority() < e2.getPriority())
			return -1;
		else if(e1.getPriority() > e2.getPriority())
			return 1;
		else 
			return 0;
	}
}
