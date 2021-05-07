package eCommerce.business.concretes;

import java.util.List;

import eCommerce.business.abstracts.UserService;
import eCommerce.core.AuthService;
import eCommerce.dataAccess.abstracts.UserDao;
import eCommerce.entities.concretes.User;

public class UserManager implements UserService {

	private UserDao userDao;
	private AuthService authService;
	
	public UserManager(UserDao userDao, AuthService authService) {
		super();
		this.userDao = userDao;
		this.authService = authService;
	}

	@Override
	public void add(User user) {
		boolean result = rules(user);
		if(result) {
			userDao.add(user);
			System.out.println("User added");
		}
	}
	
	@Override
	public void delete(User user) {
		userDao.delete(user);
		System.out.println("User deleted");
	}

	@Override
	public void update(User user) {
		userDao.update(user);
		System.out.println("User updated");
	}
	
	public boolean login(User user) {
		List<User> users = userDao.getAll();
		User userToLogin = null;
		
		for(User u : users){
			if(u.getEmail()==user.getEmail() && u.getPassword() == user.getPassword()) {
				userToLogin = u;
			}
		}
		if(userToLogin != null) {
			System.out.println("Successfully logged in");
			return true;
		}
		else {
			System.out.println("Please check your information");
			return false;
		}
	}
	
	public void addUserExternally(User user) {
		boolean result = authService.checkIfUserExists(user);
		if(result) {
			userDao.add(user);
			System.out.println("User added with Google");
		}
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}
	
	public boolean checkPasswordLength(String password) {
		if(password.length()>=6)
			return true;
		return false;
	}
	
	public boolean checkEmailFormat(String email) {
		String regexExpression = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		boolean result = email.matches(regexExpression);
		if(result)
			return true;
		else {
			System.out.println("Please enter a valid email address format");
		}
		return false;
	}
	
	public boolean checkIfEmailTaken(String email) {
		List<User> users = userDao.getAll();
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getEmail() == email) {
				System.out.println("Email has been taken");
				return false;
			}
		}
		return true;
	}
	
	public boolean checkFirstNameLength(String firstName) {
		if(firstName.length()>=2)
			return true;
		else {
			System.out.println("Please enter a first name longer than 2 chars");
			return false;
		}
	}
	
	public boolean checkSecondNameLength(String secondName) {
		if(secondName.length()>=2)
			return true;
		else {
			System.out.println("Please enter a last name longer than 2 chars");
			return false;
		}
	}
	
	public boolean rules(User user) {
		if(checkIfEmailTaken(user.getEmail()) 
				&& checkFirstNameLength(user.getFirstName()) 
				&& checkSecondNameLength(user.getLastName()))
			return true;
		return false;
	}
	
}
