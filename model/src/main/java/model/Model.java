package model;

import java.util.Observable;
import contract.IModel;

/**
 * The Class Model.
 *
 * @author Jean-Aymeric Diet
 */
public class Model extends Observable implements IModel {

	private String map;
	
	public Model() {
		this.loadMap(1);;
	}
	
	public String getMap() {
		return this.map;
	}
	
	private void setMap(final String map) {
		this.map = map;
		this.setChanged();
		this.notifyObservers();
	}

	public void loadMap(int level) {
		final DAO dao = new DAO();
		dao.open();
		this.setMap(dao.getMap(level));
		dao.close();
	}

	public Observable getObservable() {
		return this;
	}
}
