package main;

import controller.Controller;
import model.Model;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public abstract class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {						//Class to start at the beginning
		final Model model = new Model(1);								//Creating the model at the level 1
		final View view = new View(model);								//Creating the view with the previous model
		final Controller controller = new Controller(view, model);		//Creating the controller with the previous model and view
		view.setController(controller);									//Setting the previous controller to the view
	}
}