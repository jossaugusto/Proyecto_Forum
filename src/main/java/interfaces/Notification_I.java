package interfaces;

import java.util.List;

import entitys.Notification_E;

public interface Notification_I {
	// CRUD operations
	
	// List all notifications
	List<Notification_E> getAllNotifications();
	
	// Get notification by ID
	Notification_E getNotificationById(int id);
	
	// Create a new notification
	boolean createNotification(int userId, String type, String content);
	
	// Update notification information
	boolean updateNotification(Notification_E notification);
	
	// Delete notification by ID
	boolean deleteNotification(int id);
	
	// Get notifications by user ID
	List<Notification_E> getNotificationsByUserId(int userId);
	
	// Mark notification as read
	boolean markAsRead(int notificationId);
}
