package eCommerce.business.abstracts;

import java.util.List;

import eCommerce.entities.concretes.User;

public interface UserService {
	
	void add(User user);
	void delete(User user);
	void update(User user);
	void addUserExternally(User user);
	void login(String email, String password);
	List<User> getAll();
	User getById(int userId);
	User getByEmail(String email);
}
