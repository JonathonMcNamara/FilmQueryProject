package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Actor {
	
	private int actorId;
	private String firstName;
	private String lastName;
	private List<Film> films;
	
	public Actor(int actorId, String firstName, String lastName) {
		super();
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	public Actor(int actorId, String firstName, String lastName, List<Film> films) {
		super();
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.films = films;
	}
	
//	GETTERS AND SETTERS
	public int getActorId() {
		return actorId;
	}
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Film> getFilms() {
		return films;
	}
	public void setFilms(List<Film> films) {
		this.films = films;
	}
	
//	TO STRING
	@Override
	public String toString() {
		return "Actor ID: " + actorId + " First Name: " + firstName + " Last Name: " + lastName + " Films: " + films;
	}

}
