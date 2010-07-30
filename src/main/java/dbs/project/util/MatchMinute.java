package dbs.project.util;

public class MatchMinute extends Tuple<Integer, Integer> implements Comparable<MatchMinute> {

	public MatchMinute(int i, int j) {
		super(i, j);
	}

	public MatchMinute(int minute) {
		super(minute,0);
	}

	public int compareTo(MatchMinute o) {
		if(this.getFirst() < o.getFirst()) {
			return -1;
		} else if(this.getFirst() == o.getFirst()) {
			if(this.getSecond() < o.getSecond())
				return  -1;
			else if(this.getSecond() == o.getSecond())
				return 0;
			else
				return 1;
		} else {
			return 1;
		}
	}

}
