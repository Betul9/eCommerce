package eCommerce.business.abstracts;

import java.util.List;

import eCommerce.entities.concretes.User;

public interface UserService {
	
	void add(User user);
	void delete(User user);
	void update(User user);
	void addUserExternally(User user);
	boolean login(User user);
	List<User> getAll();
}
