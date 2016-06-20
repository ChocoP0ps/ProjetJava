package contract;

import java.util.ArrayList;
import java.util.Observable;


// TODO: Auto-generated Javadoc
/**
 * The Interface IModel.
 */
public interface IModel {			//Interface implemented by the Model of the MVC

	/**
			 * Gets the map.
			 *
			 * @return the map
			 */
			String getMap();				//Return the map

	/**
	 * Load map.
	 *
	 * @param level the level
	 */
	void loadMap(int level);		//Load a new map with the level 'level'

	/**
	 * Gets the observable.
	 *
	 * @return the observable
	 */
	Observable getObservable();		//Get the observable object Model
	
	/**
	 * Gets the elements list.
	 *
	 * @return the elements list
	 */
	public ArrayList<IElement> getElementsList();			//Get the element's array

	/**
	 * Down.
	 */
	void Down();								//The hero go down

	/**
	 * Up.
	 */
	void Up();									//The hero go up

	/**
	 * Right.
	 */
	void Right();								//The hero go right

	/**
	 * Left.
	 */
	void Left();								//The hero go left

	/**
	 * Modify array.
	 */
	void modifyArray();							//Update the element's array
	
	/**
	 * Shoot.
	 *
	 * @param dir the dir
	 */
	void shoot(char dir);						//Make the hero shoot at the direction 'dir'
	
	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	int getLevel();								//Getter of the level
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	int getScore();								//Getter of the score
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();							//Getter of the name
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	void setName(String name);					//Setter of the name
	
	/**
	 * Adds the name.
	 */
	void addName();								//Add the name with its linked score to the BDD
	
	/**
	 * Load best score.
	 *
	 * @param place the place
	 * @return the int
	 */
	int loadBestScore(int place);				//Load the score at the BestScore's place 'place'
	
	/**
	 * Load best name.
	 *
	 * @param place the place
	 * @return the string
	 */
	String loadBestName(int place);				//Load the name at the BestScore's place 'place'
}
