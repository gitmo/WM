package dbs.project.entity;

import java.util.Date;

public class Advisor extends Person {
    private String task;

   
    public Advisor() {}
    
    public Advisor(String first, String last, Date birth, int height, int weight) {
        super(first, last, birth, height, weight);
    }

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
}
