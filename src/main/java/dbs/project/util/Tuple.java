package dbs.project.util;

public class Tuple<T, S> {
	private T first;
	private S second;

	public Tuple() {
	}

	public Tuple(T first) {
		setFirst(first);
	}

	public T getFirst() {
		return first;
	}

	public Tuple(T first, S second) {
		setFirst(first);
		setSecond(second);
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public S getSecond() {
		return second;
	}

	public void setSecond(S second) {
		this.second = second;
	}
}
