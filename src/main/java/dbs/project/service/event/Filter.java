package dbs.project.service.event;

public interface Filter<T> {
	boolean apply(T type);
}
