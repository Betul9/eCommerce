package eCommerce.core;

import eCommerce.entities.concretes.User;

public interface AuthService {
	boolean checkIfUserExists(User user);
}
