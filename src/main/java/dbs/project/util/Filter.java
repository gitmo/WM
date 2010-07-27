package dbs.project.util;

public interface Filter<T> {
	boolean apply(T type);
}
