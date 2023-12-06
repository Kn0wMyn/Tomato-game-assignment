package com.perisic.tomato.peripherals;
/**
 * Basic class. To do: 
 * link against external database.
 * signup mechanism to create account. 
 * Encryption
 * Etc.
 * @author Marc Conrad
 *
 */
public class LoginData {
	/**
	 * Returns true if passwd matches the username given.
	 * @param username
	 * @param passwd
	 * @return
	 */
	boolean checkPassword(String username, String passwd) { 
		if( username.equals("") && passwd.equals("")) return true; 
		return false; 
		
	}
}
