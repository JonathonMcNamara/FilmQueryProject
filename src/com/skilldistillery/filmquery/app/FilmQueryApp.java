package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	Scanner kb = new Scanner(System.in);
	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

//	private void test() throws SQLException {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//	}

	private void launch() throws SQLException {
		menu();
		String userChoice = kb.nextLine();
		switch (userChoice) {
		case "1":
			filmById();
			break;
		case "2":
			filmsByKeyword();
			break;
		case "3":
			System.out.println("Quitting The Application. Good Bye");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Choice, Please Select 1, 2, or 3");
			menu();
		}
		kb.close();
	}

	private void filmById() throws SQLException {
		System.out.println("What is the id of the film you are looking for? ");
		int userChoice = kb.nextInt();
		Film film = db.findFilmById(userChoice);
		if (film == null) {
			System.out.println("Could not find a film with that id");
		} else {
			System.out.println(film);
		}
	}

	private void filmsByKeyword() throws SQLException {
		System.out.println("Please enter a keyword to search the film by: ");
		String userKeyword = kb.nextLine();
		List<Film> foundFilms = db.findFilmsByKeyword(userKeyword);
		if (foundFilms.isEmpty()) {
			System.out.println("No films found by that keyword");
		} else {
			for (Film film : foundFilms) {
				System.out.println(film);
			}
		}

	}

	private void menu() {
		System.out.println("Welcome To The Main Menu");
		System.out.println("Select the following options: ");
		System.out.println("1. Look up a film by its id ");
		System.out.println("2. Look up a film by a search keyboard ");
		System.out.println("3. Exit the application ");
	}

}
