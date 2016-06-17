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
	private IView		view;

	/** The model. */
	private IModel	model;
	
	private int first = 0;

	/**
	 * Instantiates a new controller.
	 *
	 * @param view
	 *          the view
	 * @param model
	 *          the model
	 */
	public Controller(final IView view, final IModel model) {
		this.setView(view);
		this.setModel(model);
		this.model.loadMap(this.model.getLevel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see contract.IController#control()
	 */
	public void control() {
		//this.view.printMessage("Appuyer sur les touches 'E', 'F', 'D' ou 'I', pour afficher Hello world dans la langue d votre choix.");
	}

	/**
	 * Sets the view.
	 *
	 * @param view
	 *          the new view
	 */
	private void setView(final IView view) {
		this.view = view;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 *          the new model
	 */
	private void setModel(final IModel model) {
		this.model = model;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#orderPerform(contract.ControllerOrder)
	 */
	public void orderPerform(final ControllerOrder controllerOrder) {
		switch (controllerOrder) {
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
		this.model.modifyArray();
		if(this.model.getLevel() == 1 && this.first == 1){
			this.first = 0;
			this.model.setName(this.view.printMessage());
			this.model.addName();
		}
		if(this.model.getLevel() != 1)
			this.first = 1;
	}

}
