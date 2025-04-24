package dao;

import interfaces.*;
import models.*;

public class MySQLDAOFactory extends DAOFactory{

	@Override
	public User_I getUser() {
		return new User_M();
	}

	@Override
	public Category_I getCategory() {
		return new Category_M();
	}

	@Override
	public Topic_I getTopic() {
		return new Topic_M();
	}

	@Override
	public Reply_I getReply() {
		return new Reply_M();
	}

	@Override
	public Notification_I getNotification() {
		return new Notification_M();
	}


	
}
