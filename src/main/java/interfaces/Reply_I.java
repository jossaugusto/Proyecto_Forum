package interfaces;

import java.util.List;

import entitys.Reply_E;

public interface Reply_I {
	// CRUD operations
	
	// List all replies
	List<Reply_E> getAllReplies();
	
	// Get reply by ID
	Reply_E getReplyById(int id);
	
	// Create a new reply
	boolean createReply(Reply_E reply);
	
	// Update reply information
	boolean updateReply(Reply_E reply);
	
	// Delete reply by ID
	boolean deleteReply(int id);
	
	// Get replies by topic ID
	List<Reply_E> getRepliesByTopicId(int topicId);
	
	// Get replies by user ID
	List<Reply_E> getRepliesByUserId(int userId);
	
	int getQuantityReplyByTopicId(int id_tema);
}
