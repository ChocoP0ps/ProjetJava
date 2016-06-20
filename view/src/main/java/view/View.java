package view;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;


// TODO: Auto-generated Javadoc
/**
 * The Class View.
 */
public class View implements IView, Runnable {					//Class view of the MVC design pattern

	/** The view frame. */
					private final ViewFrame viewFrame;				//Object ViewFrame


	/**
	 * Instantiates a new view.
	 *
	 * @param model the model
	 */
	public View(final IModel model) {							//Constructor of the view
		this.viewFrame = new ViewFrame(model,"Lorann");			//Instantiation of the JFrame
		SwingUtilities.invokeLater(this);						//Execute the view's thread 
	}


	/**
	 * Key code to controller order.
	 *
	 * @param keyCode the key code
	 * @return the controller order
	 */
	protected static ControllerOrder keyCodeToControllerOrder(final int keyCode) {		//Read the key pressed end convert it into a ControllerOrder
		switch (keyCode) {
			case KeyEvent.VK_LEFT:
				return ControllerOrder.Left;
			case KeyEvent.VK_RIGHT:
				return ControllerOrder.Right;
			case KeyEvent.VK_UP:
				return ControllerOrder.Up;
			case KeyEvent.VK_DOWN:
				return ControllerOrder.Down;
			case KeyEvent.VK_ESCAPE :
				return ControllerOrder.Exit;
			case KeyEvent.VK_Z :
				return ControllerOrder.Z;
			case KeyEvent.VK_Q :
				return ControllerOrder.Q;
			case KeyEvent.VK_S:
				return ControllerOrder.S;
			case KeyEvent.VK_D :
				return ControllerOrder.D;
			default:
				return ControllerOrder.Nothing;
		}
	}

	/* (non-Javadoc)
	 * @see contract.IView#printMessage()
	 */
	public String printMessage() {				//Start the ViewFrame's method printMessage()
		return this.viewFrame.printMessage();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {							//Show the window
		this.viewFrame.setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see contract.IView#closeWindow()
	 */
	public void closeWindow(){					//Close the window
		this.viewFrame.dispose();
	}

	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	public void setController(final IController controller) {			//Setter of the Controller
		this.viewFrame.setController(controller);
	}
}
