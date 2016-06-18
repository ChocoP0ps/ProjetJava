package contract;

import java.util.ArrayList;
import java.util.Observable;


public interface IModel {			//Interface implemented by the Model of the MVC

	String getMap();				//Return the map

	void loadMap(int level);		//Load a new map with the level 'level'

	Observable getObservable();		//Get the observable object Model
	
	public ArrayList<IElement> getElementsList();			//Get the element's array

	void Down();								//The hero go down

	void Up();									//The hero go up

	void Right();								//The hero go right

	void Left();								//The hero go left

	void modifyArray();							//Update the element's array
	
	void shoot(char dir);						//Make the hero shoot at the direction 'dir'
	
	int getLevel();								//Getter of the level
	
	int getScore();								//Getter of the score
	
	String getName();							//Getter of the name
	
	void setName(String name);					//Setter of the name
	
	void addName();								//Add the name with its linked score to the BDD
	
	int loadBestScore(int place);				//Load the score at the BestScore's place 'place'
	
	String loadBestName(int place);				//Load the name at the BestScore's place 'place'
}
