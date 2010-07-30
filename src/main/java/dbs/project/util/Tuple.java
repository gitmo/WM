package dbs.project.util;

public class Tuple<T, S> {
	protected T first;
	protected S second;

	public Tuple() {
	}

	public Tuple(T first) {
		setFirst(first);
	}

	public T getFirst() {
		return this.first;
	}

	public Tuple(T first, S second) {
		setFirst(first);
		setSecond(second);
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public S getSecond() {
		return this.second;
	}

	public void setSecond(S second) {
		this.second = second;
	}
}
