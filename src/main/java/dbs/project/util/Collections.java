package dbs.project.util;

import java.util.LinkedList;
import java.util.List;

import dbs.project.util.collections.Filter;

public class Collections {
	/**
	 * filters a MatchEvent ĺist by a given filter implementation
	 * 
	 * @param list
	 * @param filter
	 * @return filtered list
	 */
	public static <T> List<T> filter(List<T> list, Filter<T> filter) {
		List<T> filteredEvents = new LinkedList<T>();
		for (T event : list)
			if (filter.apply(event))
				filteredEvents.add(event);

		return filteredEvents;
	}

	@SuppressWarnings("unchecked")
	public static <T, E> void filterAndChangeType(List<T> list,
			Filter<T> filter, List<E> newList) {
		for (T item : list)
			if (filter.apply(item))
				newList.add((E) item);
	}
}
