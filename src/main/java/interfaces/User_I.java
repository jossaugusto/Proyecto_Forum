package interfaces;

import java.util.List;

import entitys.User_E;

public interface User_I {
	// CRUD operations
	
	// List all users
	List<User_E> getAllUsers(String keyword,String order);
	
	List<User_E> getAllDeletedUsers(String keyword, String order);
	
	// Get user by ID
	User_E getUserById(int id);
	
	// Get user by email
	User_E getUserByEmail(String email);
	
	// Create a new user
	boolean createUser(User_E user);
	
	// Update user information
	boolean updateUser(User_E user, boolean updatePassword);
	
	// Delete user by ID
	boolean deleteUser(int id);
	
	// Restore user by ID
	boolean restoreUser(int id);
	
	// Validate user credentials
	boolean validateUser(String email, String password);
	
	// Count quantity of users
	int countUsers();
}
