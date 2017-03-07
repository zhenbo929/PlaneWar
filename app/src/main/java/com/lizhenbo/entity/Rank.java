package com.lizhenbo.entity;

public class Rank {

	private String name;
	private long score;
	
	public Rank() {
		super();
	}

	public Rank(String name, long score) {
		super();
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Rank [name=" + name + ", score=" + score + "]";
	}
}
