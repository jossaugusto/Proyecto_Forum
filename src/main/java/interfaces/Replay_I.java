package interfaces;

import java.util.List;

import entitys.Replay_E;

public interface Replay_I {
	// CRUD operations
	
	// List all replies
	List<Replay_E> getAllReplies();
	
	// Get reply by ID
	Replay_E getReplyById(int id);
	
	// Create a new reply
	boolean createReply(Replay_E reply);
	
	// Update reply information
	boolean updateReply(Replay_E reply);
	
	// Delete reply by ID
	boolean deleteReply(int id);
	
	// Get replies by topic ID
	List<Replay_E> getRepliesByTopicId(int topicId);
	
	// Get replies by user ID
	List<Replay_E> getRepliesByUserId(int userId);
}
