package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

  @Override
  public Film findFilmById(int filmId) throws SQLException {
	Film film = null;
	String user = "student";
	String pass = "student";
	Connection conn = DriverManager.getConnection(URL, user, pass);
	
	
	String sql = "SELECT * FROM film WHERE film_id = ?"; 
	
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setInt(1, filmId);
	ResultSet filmResult = ps.executeQuery();
	if(filmResult.next()) {
		int id = filmResult.getInt("id");
		String title = filmResult.getString("title");
		String desc = filmResult.getString("description");
		String releaseYear = filmResult.getString("releaseYear");
		int languageId = filmResult.getInt("languageId");
		int rentalDuration = filmResult.getInt("rentalDuration");
		double rentalRate = filmResult.getDouble("rentalRate");
		int length = filmResult.getInt("length");
		double replacementCost = filmResult.getDouble("replacementCost");
		double rating = filmResult.getDouble("rating");
		Object features = filmResult.getObject("features");
		
		film = new Film(id, title, desc, releaseYear, languageId, rentalDuration, rentalRate, length, replacementCost, rating, features);	
	}
	
	return film;
  }

@Override
public Actor findActorById(int actorId) throws SQLException {
	Actor actor = null;
	String user = "student";
	String pass = "student";
	Connection conn = DriverManager.getConnection(URL, user, pass);
	
	String sql = "SELECT * FROM actor WHERE id = ?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setInt(1, actorId);
	ResultSet actorResult = ps.executeQuery();
	if(actorResult.next()) {
		int id = actorResult.getInt("id");
		String firstName = actorResult.getString("first_name");
		String lastName = actorResult.getString("last_name");
		
		actor = new Actor(id, firstName, lastName);
	}
	
	return actor;
}

@Override
public List<Actor> findActorsByFilmId(int filmId) {
	// TODO Auto-generated method stub
	return null;
}

}
