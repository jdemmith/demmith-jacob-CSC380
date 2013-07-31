package servlets;

public class Restaurant {
	String name;
	int id;
	Menu menu;

	public Restaurant(String name, int id, Menu menu2 ) {
		this.name= name;
		this.id=id;
		this.menu=menu2;
	}
}
