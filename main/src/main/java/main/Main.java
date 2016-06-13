package main;

import controller.Controller;
import model.Model;
import view.View;

public abstract class Main {

	public static void main(final String[] args) {
		final Model model = new Model(1);
		final View view = new View(model);
		final Controller controller = new Controller(view, model);
		view.setController(controller);
		controller.control();
	}
}