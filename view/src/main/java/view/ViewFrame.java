package view;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import contract.IController;
import contract.IModel;


// TODO: Auto-generated Javadoc
/**
 * The Class ViewFrame.
 */
public class ViewFrame extends JFrame implements KeyListener {			//Class JFrame which implements the keyListener's Interface

	/** The model. */
			private IModel						model;				//Object Model of the MVC

	/** The controller. */
	private IController				controller;				//Object Controller of the MVC

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -697358409737458175L;			//Serial Version


	/**
	 * Instantiates a new view frame.
	 *
	 * @param model the model
	 * @throws HeadlessException the headless exception
	 */
	public ViewFrame(final IModel model) throws HeadlessException {										//Constructor whith the Model
		this.buildViewFrame(model);
	}

	/**
	 * Instantiates a new view frame.
	 *
	 * @param model the model
	 * @param gc the gc
	 */
	public ViewFrame(final IModel model, final GraphicsConfiguration gc) {								//Constructor with the model and the graphic's configuration
		super(gc);
		this.buildViewFrame(model);
	}

	/**
	 * Instantiates a new view frame.
	 *
	 * @param model the model
	 * @param title the title
	 * @throws HeadlessException the headless exception
	 */
	public ViewFrame(final IModel model, final String title) throws HeadlessException {					//Constructor with the model and the window's title
		super(title);
		this.buildViewFrame(model);
	}

	/**
	 * Instantiates a new view frame.
	 *
	 * @param model the model
	 * @param title the title
	 * @param gc the gc
	 */
	public ViewFrame(final IModel model, final String title, final GraphicsConfiguration gc) {			//Constructor with every previous fields
		super(title, gc);
		this.buildViewFrame(model);
	}


	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	private IController getController() {									//Getter of the controller
		return this.controller;
	}

	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	protected void setController(final IController controller) {			//Setter of the controller
		this.controller = controller;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	protected IModel getModel() {											//Getter of the Model
		return this.model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	private void setModel(final IModel model) {								//Setter of the model
		this.model = model;
	}


	/**
	 * Builds the view frame.
	 *
	 * @param model the model
	 */
	private void buildViewFrame(final IModel model) {						//Window's constructor
		this.setModel(model);									//Setting the model
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Setting the action to perform at the exit of the window
		this.setResizable(false);								//Setting the resizable to false
		this.addKeyListener(this);								//Adding the Key Listener
		this.setContentPane(new ViewPanel(this));				//Creating the JPanel
		this.setSize(1280,850);									//Setting the size of the Window
		this.setLocationRelativeTo(null);						//The window will not spawn at a specific location
	}
	
	/**
	 * Prints the message.
	 *
	 * @return the string
	 */
	public String printMessage() {			//Brings a Pop-Up which request the Player's pseudo
		return JOptionPane.showInputDialog(null, "Rentrez votre pseudo !", JOptionPane.INFORMATION_MESSAGE);
	}

	//Method of the Key Listener Interface
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(final KeyEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(final KeyEvent e) {			//Method which start at the pressing of a key
		this.getController().orderPerform(View.keyCodeToControllerOrder(e.getKeyCode()));		//Send the key pressed to the converter an to the interpreter
		this.getContentPane().repaint(0, 0, this.getWidth(), this.getHeight());					//Update the window
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(final KeyEvent e) {

	}
}
