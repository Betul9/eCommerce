package eCommerce.dataAccess.concretes;

import java.util.ArrayList;
import java.util.List;
import eCommerce.dataAccess.abstracts.UserDao;
import eCommerce.entities.concretes.User;

public class HibernateUserDao implements UserDao{

	private List<User> users = new ArrayList<>();
	
	@Override
	public void add(User user) {
		users.add(user);
	}

	@Override
	public void update(User user) {
		User userToUpdate = null;
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getUserId() == user.getUserId())
				userToUpdate = users.get(i);
		}
		
		userToUpdate.setFirstName(user.getFirstName());
		userToUpdate.setLastName(user.getLastName());
		userToUpdate.setEmail(user.getEmail());
		userToUpdate.setPassword(user.getPassword());
	}

	@Override
	public void delete(User user) {
		User userToDelete = null;
		for (User u : users) {
			if(u.getUserId() == user.getUserId())
				userToDelete = u;
		}
		
		users.remove(userToDelete);
	}

	@Override
	public User getById(int userId) {
		User userToGet = null;
		for (User u : users) {
			if(u.getUserId() == userId)
				userToGet = u;
		}
		
		return userToGet;
	}

	@Override
	public List<User> getAll() {
		return users;
	}

}
