package data.dao.factory;

import data.dao.special.LevelDAO;
import data.dao.special.SingleGameDAO;

public class DaoFactory {
	
	private SingleGameDAO singleGameDAO = new SingleGameDAO();
	
	private LevelDAO levelDAO = new LevelDAO();
	
	private static DaoFactory instance = null;

	public static synchronized DaoFactory getInstance() {
		if (instance == null)
			instance = new DaoFactory();
		return instance;
	}
	
	public SingleGameDAO getSingleGameDAO() {
		return this.singleGameDAO;
	}
	
	public LevelDAO getLevelDAO(){
		return this.levelDAO;
	}
	
}
