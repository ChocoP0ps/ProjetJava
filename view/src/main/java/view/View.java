package view;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;


public class View implements IView, Runnable {					//Class view of the MVC design pattern

	private final ViewFrame viewFrame;				//Object ViewFrame


	public View(final IModel model) {							//Constructor of the view
		this.viewFrame = new ViewFrame(model,"Lorann");			//Instantiation of the JFrame
		SwingUtilities.invokeLater(this);						//Execute the view's thread 
	}


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

	public String printMessage() {				//Start the ViewFrame's method printMessage()
		return this.viewFrame.printMessage();
	}

	public void run() {							//Show the window
		this.viewFrame.setVisible(true);
	}
	
	public void closeWindow(){					//Close the window
		this.viewFrame.dispose();
	}

	public void setController(final IController controller) {			//Setter of the Controller
		this.viewFrame.setController(controller);
	}
}
