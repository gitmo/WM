package dbs.project.util.collections;

public interface Filter<T> {
	boolean apply(T item);
}
