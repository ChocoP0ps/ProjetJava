package contract;

import java.util.ArrayList;
import java.util.Observable;
import elements.Element;

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

	ArrayList<Element> getElementsList();

	void Down();

	void Up();

	void Right();

	void Left();

	void modifyArray();
}
