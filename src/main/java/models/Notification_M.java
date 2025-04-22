package models;

import java.util.List;

import entitys.Notification_E;
import interfaces.Notification_I;

public class Notification_M implements Notification_I{

	@Override
	public List<Notification_E> getAllNotifications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification_E getNotificationById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createNotification(int userId, String type, String content) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateNotification(Notification_E notification) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteNotification(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Notification_E> getNotificationsByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean markAsRead(int notificationId) {
		// TODO Auto-generated method stub
		return false;
	}

}
