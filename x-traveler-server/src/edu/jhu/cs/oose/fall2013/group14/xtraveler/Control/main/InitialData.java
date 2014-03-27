package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main;

import org.hibernate.SessionFactory;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;

/**
 * InitialData class initial some data into database. It is convenient for demo
 * presentation.
 * 
 * @author iamrick86
 * @version java7
 */
public class InitialData {
	SessionFactory sessionFactory;
	UserDAO userdao;
	PlaceDAO placedao;

	public InitialData(SessionFactory sf) {
		this.sessionFactory = sf;
		this.userdao = new UserDAO(sessionFactory);
		this.placedao = new PlaceDAO(sessionFactory);
	}

	public void insertData() {
		userdao.insertUser("Peter", "101010", 20, "ee@qo.com", "male");
		userdao.insertUser("Gray", "121212", 23, "90@kk.com", "male");
		userdao.insertUser("Omika", "303030", 27, "so@pp.com", "female");
		userdao.insertUser("Henry", "141414", 25, "12@ga.com", "male");
		userdao.insertUser("Yamoka", "150510", 20, "me@sk.com", "female");
		userdao.insertUser("Bob", "001320", 40, "sse@qf.com", "male");
		userdao.insertUser("Lynn", "501320", 36, "qe@xh.com", "female");
		userdao.insertUser("Rose", "381270", 24, "ppe@jj.com", "female");
		userdao.insertUser("Jane", "012340", 27, "sp@vy.com", "female");
		userdao.insertUser("Ivy", "053320", 29, "gg@xf.com", "male");

		placedao.insertPlace("Inner Harbor", "Baltimore", "Maryland",
				"One of the most photographed and visited areas of the city.",
				"Fishing", "http://biostat.jhsph.edu/~shachen/images/1.png", 5);
		placedao.insertPlace(
				"Towson Town Certer",
				"Baltimore",
				"Maryland",
				" It is the largest indoor shopping mall in Maryland",
				"Shopping",
				"http://www.ventureworkscapital.com/pics/shopping_mall_1_300.jpg",
				4);
		placedao.insertPlace(
				"National Aquarium",
				"Baltimore",
				"Maryland",
				" It exhibits a variety of species in their naturalistic habitats",
				"Fishing", "http://img1.1dufish.com/image/6-16/ht4.jpg", 4);
		placedao.insertPlace(
				"Bear Mountain",
				"New York",
				"New York",
				"It is one of best-known peaks and it lends its name to a nearby bridge",
				"Hiking",
				"http://clearspringsofgeorgia.com/csg_images/SmallView200.jpg",
				4);
		placedao.insertPlace(
				"Macys",
				"New York",
				"New York",
				"It is included many famous brands.",
				"Shopping",
				"http://www.mysouthlandmall.com/m/images/shopping-small-macys.jpg",
				5);

		placedao.addToWishList("Bob", "Macys");
		placedao.addToVisitedList("Bob", "Inner Harbor");
		placedao.addToVisitedList("Bob", "Bear Mountain");
		placedao.addToWishList("Lynn", "Inner Harbor");
		placedao.addToVisitedList("Lynn", "Towson Town Certer");
		placedao.addToVisitedList("Rose", "National Aquarium");
		placedao.addToWishList("Rose", "Towson Town Certer");
		placedao.addToVisitedList("Ivy", "Inner Harbor");
		placedao.addToWishList("Ivy", "Bear Mountain");
		placedao.addToVisitedList("Jane", "Macys");
		placedao.addToWishList("Yamoka", "National Aquarium");
		placedao.addToVisitedList("Omika", "Bear Mountain");
		placedao.addToWishList("Peter", "Macys");
		placedao.addToVisitedList("Gray", "National Aquarium");

	}

}
