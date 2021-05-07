package eCommerce.dataAccess.abstracts;

import java.util.List;

import eCommerce.entities.concretes.User;

public interface UserDao {

	void add(User user);
	void update(User user);
	void delete(User user);
	User getById(int userId);
	List<User> getAll();
}
