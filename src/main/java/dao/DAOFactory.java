package dao;

import interfaces.*;

public abstract class DAOFactory {
	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;

	// List all the DAO types here
	public abstract User_I getUser();
	public abstract Category_I getCatogory();
	public abstract Topic_I getTopic();
	public abstract Replay_I getReply();
	public abstract Notification_I getNotification();

	public static DAOFactory getDAOFactory(int factory) {
		switch (factory) {
			case MYSQL:
				return new MySQLDAOFactory();
			case SQLSERVER:
				//return new SQLServerDAOFactory();
			default:
				return null;
		}
	}
}
