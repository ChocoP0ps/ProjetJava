package contract;

import java.util.ArrayList;
import java.util.Observable;

import elements.Element;
import elements.Hero;

/**
 * The Interface IModel.
 *
 * @author Jean-Aymeric Diet
 */
public interface IModel {

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	String getMap();

	/**
	 * Load the message.
	 *
	 * @param key
	 *          the key
	 */
	void loadMap(int level);

	/**
	 * Gets the observable.
	 *
	 * @return the observable
	 */
	Observable getObservable();

	public ArrayList<Element> getElementsList();
	
	public Hero getLorann();

	void Right(Hero lorann);

	void Left(Hero lorann);

	void Up(Hero lorann);

	void Down(Hero lorann);
}
