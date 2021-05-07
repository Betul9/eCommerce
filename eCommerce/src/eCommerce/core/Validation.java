package eCommerce.core;

public class Validation {
	public static boolean Run(Boolean... logics) {
		for (Boolean logic : logics) {
			if (logic != true) {
				return logic;
			}
		}
		return true;
	}
}
