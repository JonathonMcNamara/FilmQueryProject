package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

		String sql = "SELECT * " + " FROM film JOIN language ON film.language_id = language.id " + " WHERE film.id =?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, filmId);
		ResultSet filmResult = ps.executeQuery();
		if (filmResult.next()) {
			int id = filmResult.getInt("id");
			String title = filmResult.getString("title");
			String desc = filmResult.getString("description");
			String releaseYear = filmResult.getString("release_year");
			String languageId = filmResult.getString("name");
			int rentalDuration = filmResult.getInt("rental_duration");
			double rentalRate = filmResult.getDouble("rental_rate");
			int length = filmResult.getInt("length");
			double replacementCost = filmResult.getDouble("replacement_cost");
			String rating = filmResult.getString("rating");
			Object features = filmResult.getObject("special_features");

			film = new Film(id, title, desc, releaseYear, languageId, rentalDuration, rentalRate, length,
					replacementCost, rating, features);
			film.setFilmActors(findActorsByFilmId(film.getId()));
			
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		List<Actor> actorList = new ArrayList<>();
		Actor actor = null;
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT * FROM actor WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, actorId);
		ResultSet actorResult = ps.executeQuery();
		if (actorResult.next()) {
			int id = actorResult.getInt("id");
			String firstName = actorResult.getString("first_name");
			String lastName = actorResult.getString("last_name");

			actor = new Actor(id, firstName, lastName);
		}

		return actor;
	}
	@Override
	public List<Film> findFilmsByKeyword(String userKeyword) throws SQLException {
		List<Film> foundFilms = new ArrayList<Film>();
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		
		String sql = "SELECT * " + " FROM film JOIN language ON film.language_id = language.id "
					+ " WHERE film.title LIKE ? OR film.description LIKE ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		String userQuery = "%" + userKeyword + "%";
		ps.setString(1, userQuery);
		ps.setString(2, userQuery);
		ResultSet filmResult = ps.executeQuery();
		while(filmResult.next()) {
			int id = filmResult.getInt("id");
			String title = filmResult.getString("title");
			String desc = filmResult.getString("description");
			String releaseYear = filmResult.getString("release_year");
			String languageId = filmResult.getString("name");
			int rentalDuration = filmResult.getInt("rental_duration");
			double rentalRate = filmResult.getDouble("rental_rate");
			int length = filmResult.getInt("length");
			double replacementCost = filmResult.getDouble("replacement_cost");
			String rating = filmResult.getString("rating");
			Object features = filmResult.getObject("special_features");

			Film film = new Film(id, title, desc, releaseYear, languageId, rentalDuration, rentalRate, length,
					replacementCost, rating, features);
			film.setFilmActors(findActorsByFilmId(film.getId()));
			foundFilms.add(film);
			
		}
		
		return foundFilms;
		
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		// TODO Auto-generated method stub
		List<Actor> filmActors = new ArrayList<>();
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT film.title, film.release_year, film.rating, film.description, actor.first_name, actor.last_name "
					+ "FROM film "
					+ "JOIN film_actor ON film.id = film_actor.film_id "
					+ "JOIN actor ON film_actor.actor_id = actor.id "
					+ "WHERE film.id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, filmId);
		ResultSet filmActorsResult = ps.executeQuery();
		while(filmActorsResult.next()) {
			String firstName = filmActorsResult.getString("first_name");
			String lastName = filmActorsResult.getString("last_name");

			Actor actor = new Actor(firstName, lastName);
			filmActors.add(actor);
		}
		return filmActors;
	}

}
