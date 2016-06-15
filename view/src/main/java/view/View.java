package view;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;

/**
 * The Class View.
 *
 * @author Jean-Aymeric Diet
 */
public class View implements IView, Runnable {

	/** The frame. */
	private final ViewFrame viewFrame;

	

	/**
	 * Instantiates a new view.
	 *
	 * @param model
	 *          the model
	 */
	public View(final IModel model) {
		this.viewFrame = new ViewFrame(model,"Lorann");
		SwingUtilities.invokeLater(this);
	}

	/**
	 * Key code to controller order.
	 *
	 * @param keyCode
	 *          the key code
	 * @return the controller order
	 */
	protected static ControllerOrder keyCodeToControllerOrder(final int keyCode) {
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

	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IView#printMessage(java.lang.String)
	 */
	public void printMessage(final String message) {
		this.viewFrame.printMessage(message);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		this.viewFrame.setVisible(true);
	}
	
	public void closeWindow(){
		this.viewFrame.dispose();
	}

	/**
	 * Sets the controller.
	 *
	 * @param controller
	 *          the new controller
	 */
	public void setController(final IController controller) {
		this.viewFrame.setController(controller);
	}
}
