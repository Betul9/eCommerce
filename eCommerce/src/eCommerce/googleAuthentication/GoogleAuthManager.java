package eCommerce.googleAuthentication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eCommerce.entities.concretes.User;

public class GoogleAuthManager {
	
	//to simulate the list of google accounts
	User u1 = new User(5, "Feyza", "Sarý", "feyza@feyza.com", "12345");
	User u2 = new User(1, "Betül", "Sarý", "betul@betul.com", "12345");
	
	List<User> googleAccounts = new ArrayList<User>(Arrays.asList(u1,u2));
	
	public boolean checkIfUserExists(User user) {
		User userToCheck = null;
		for (int i = 0; i < googleAccounts.size(); i++) {
			if(user.getEmail() == googleAccounts.get(i).getEmail() && user.getPassword() == googleAccounts.get(i).getPassword()) {
				userToCheck = googleAccounts.get(i);
			}
		}
		
		if(userToCheck != null) {
			System.out.println("Google account has been verified");
			return true;
		}
		else {
			System.out.println("Google account does not exist");
			return false;
		}
	}
}
