package repositories.user;

import java.util.List;

import models.User;

public interface UserRepository {

	User getUser(int userId);
	
	User getUserByEmail(String email);
	
	List<User> getUsers(int page);
	
	User createUser(String email, String name, String surname, String password, boolean isAdmin);
	
	User updateUser(int userId, String email, String name, String surname, boolean isAdmin);
	
	
}
