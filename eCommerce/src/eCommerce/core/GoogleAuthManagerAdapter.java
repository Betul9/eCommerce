package eCommerce.core;

import eCommerce.entities.concretes.User;
import eCommerce.googleAuthentication.GoogleAuthManager;

public class GoogleAuthManagerAdapter implements AuthService{
	
	public boolean checkIfUserExists(User user) {
		GoogleAuthManager googleAuth = new GoogleAuthManager();
		boolean result = googleAuth.checkIfUserExists(user);
		if(result)
			return true;
		return false;
	}
}
