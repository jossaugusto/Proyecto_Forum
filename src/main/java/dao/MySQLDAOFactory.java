package dao;

import interfaces.*;
import models.*;

public class MySQLDAOFactory extends DAOFactory{

	@Override
	public User_I getUser() {
		return new User_M();
	}

	@Override
	public Category_I getCatogory() {
		return new Category_M();
	}

	@Override
	public Topic_I getTopic() {
		return new Topic_M();
	}

	@Override
	public Replay_I getReply() {
		return new Replay_M();
	}

	@Override
	public Notification_I getNotification() {
		return new Notification_M();
	}


	
}
