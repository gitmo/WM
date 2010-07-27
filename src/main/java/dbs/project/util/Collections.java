package dbs.project.util;

import java.util.LinkedList;
import java.util.List;

public class Collections {
	/**
	 * filters a MatchEvent ĺist by a given filter implementation
	 * @param events
	 * @param filter
	 * @return filtered list
	 */
	public static <T> List<T> filter(List<T> events, Filter<T> filter) {
		List<T> filteredEvents = new LinkedList<T>();
		for(T event : events)
			if(filter.apply(event))
				filteredEvents.add(event);
		
		return filteredEvents;
	}
	
	@SuppressWarnings("unchecked")
	public static <T,E> List<E> filterAndChangeType(List<T> events, Filter<T> filter, List<E> newEvents) {
		for(T event : events)
			if(filter.apply(event))
				newEvents.add((E)event);
		
		return newEvents;
	}
}
