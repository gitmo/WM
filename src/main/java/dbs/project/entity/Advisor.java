package dbs.project.entity;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Advisor extends Person {
	private String task;

	public Advisor() {}

	public Advisor(String firstname, String lastname, Date birthday, int height, int weight, String task) {
		super(firstname, lastname, birthday, height, weight);
		setTask(task);
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
}
