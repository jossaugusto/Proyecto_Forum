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
	
	// Create a new sub-reply
	boolean createSubReply(Reply_E reply);
	
	// Update reply information
	boolean updateReply(Reply_E reply);
	
	// Delete reply by ID
	boolean deleteReply(int id);
	
	// Get replies by topic ID
	List<Reply_E> getRepliesByTopicId(int topicId);
	
	// Get replies by topic ID and Parent ID is not null
	//List<Reply_E> getRepliesByTopicIdAndParentIdNotNull(int topicId);
	
	// Get replies by user ID
	List<Reply_E> getRepliesByUserId(int userId);
	
	// Quantity of replies by topic ID
	int getQuantityReplyByTopicId(int id_tema);
	
	// Get replies by parent reply ID
	List<Reply_E> getRepliesByParentId(int parentId);
	
	// Get the number of replies
	int countReplies();
}
