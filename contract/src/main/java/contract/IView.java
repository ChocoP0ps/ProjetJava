package contract;


public interface IView {						//Interface implemented by the view of the MVC

	String printMessage();			//Brings a Pop-Up which request the Player's Pseudo	
	
	void closeWindow();				//Exit the game
}