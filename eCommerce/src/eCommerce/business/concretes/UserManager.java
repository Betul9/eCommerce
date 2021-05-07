package eCommerce.business.concretes;

import java.util.List;

import eCommerce.business.abstracts.UserService;
import eCommerce.core.AuthService;
import eCommerce.core.Validation;
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
		boolean result = Validation.Run(checkIfEmailTaken(user.getEmail()), 
				checkFirstNameLength(user.getFirstName()), 
				checkSecondNameLength(user.getLastName()),
				checkEmailFormat(user.getEmail()),
				checkPasswordLength(user.getPassword()));
		if(result) {
			userDao.add(user);
			System.out.println("User added");
			System.out.println("Verification email has been sent");
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
	
	public void addUserExternally(User user) {
		boolean result = authService.checkIfUserExists(user);
		if(result) {
			userDao.add(user);
			System.out.println("User added with Google");
		}
	}

	
	public void login(String email, String password) {
		User userToLogin = userDao.getByEmail(email);
		
		if(userToLogin != null && userToLogin.getPassword() == password) {
			System.out.println("Successfully logged in");
		}
		else {
			System.out.println("Please check your information");
		}
	}
	
	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}
	
	public boolean checkPasswordLength(String password) {
		if(password.length()>=6)
			return true;
		else {
			System.out.println("Please enter a password longer than 6 chars");
			return false;
		}
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
		if(userDao.getByEmail(email) != null) {
			System.out.println("Email has been taken");
			return false;
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

	@Override
	public User getById(int userId) {
		return userDao.getById(userId);
	}

	@Override
	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}
	
}
