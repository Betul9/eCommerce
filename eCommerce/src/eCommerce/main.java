package eCommerce;

import eCommerce.business.abstracts.UserService;
import eCommerce.business.concretes.UserManager;
import eCommerce.core.GoogleAuthManagerAdapter;
import eCommerce.dataAccess.concretes.HibernateUserDao;
import eCommerce.entities.concretes.User;

public class main {

	public static void main(String[] args) {
	
		UserService userService = new UserManager(new HibernateUserDao(), 
				new GoogleAuthManagerAdapter());
		
		User user = new User(1, "Betul", "Sar�", "betul@betul.com", "123456");
		User user2 = new User(5, "abc", "abc", "abc@abc.com", "5641");
		
		userService.addUserExternally(user);
		
		userService.login(user2.getEmail(), user2.getPassword());
	}

}
