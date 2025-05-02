package interfaces;

import java.util.List;

import entitys.Topic_E;

public interface Topic_I {
	// CRUD operations
	
	// List all topics
	List<Topic_E> getAllTopics(String keyword, String order);
	
	List<Topic_E> getAllDeletedTopics(String keyword, String order);
	
	// Get topic by ID
	Topic_E getTopicById(int id);
	
	// Create a new topic
	boolean createTopic(Topic_E topic);
	
	// Update topic information
	boolean updateTopic(Topic_E topic);
	
	// Delete topic by ID
	boolean deleteTopic(int id);
	
	boolean restoreTopic(int id);
	
	// Get topics by category ID
	List<Topic_E> getTopicsByCategoryId(int categoryId);
	
	// Get topics by user ID
	List<Topic_E> getTopicsByUserId(int userId);
	
	void updateTopicViews(int id_tema);
	
	// Get the number of topics
	int countTopics();
	
}
