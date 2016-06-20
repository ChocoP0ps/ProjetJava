package controller;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;


// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
public class Controller implements IController {

	/** The view. */
	private IView		view;			//Object view of the MVC

	/** The model. */
	private IModel	model;				//Object model of the MVC
	
	/** The first. */
	private int first = 0;


	/**
	 * Instantiates a new controller.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public Controller(final IView view, final IModel model) {			//Constructor
		this.setView(view);								//Setting the view of the MVC
		this.setModel(model);							//Setting the model of the MVC
		this.model.loadMap(this.model.getLevel());		//Load the first map
	}

	/**
	 * Sets the view.
	 *
	 * @param view the new view
	 */
	private void setView(final IView view) {			//View's setter
		this.view = view;
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	private void setModel(final IModel model) {			//Model's setter
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see contract.IController#orderPerform(contract.ControllerOrder)
	 */
	public void orderPerform(final ControllerOrder controllerOrder) {		//Listen the key pressing
		switch (controllerOrder) {				//Switch of the different key
			case Down:
				this.model.Down();
				break;
			case Up:
				this.model.Up();
				break;
			case Right:
				this.model.Right();
				break;
			case Left:
				this.model.Left();
				break;
			case Exit :
				this.view.closeWindow();
				break;
			case Nothing:
				break;
			case Z:
				this.model.shoot('Z');
				break;
			case Q:
				this.model.shoot('Q');
				break;
			case S:
				this.model.shoot('S');
				break;
			case D:
				this.model.shoot('D');
				break;
		}
		this.model.modifyArray();				//Update the element's array
		if(this.model.getLevel() == 1 && this.first == 1){			//If the hero is dead
			this.first = 0;
			this.model.setName(this.view.printMessage());			//request his pseudo
			this.model.addName();									//Setting his pseudo to the model
		}
		if(this.model.getLevel() != 1)
			this.first = 1;
	}

}
