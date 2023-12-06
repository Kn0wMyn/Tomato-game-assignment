package com.perisic.tomato.peripherals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



/**
 * Basic class. To do: 
 * link against external database.
 * signup mechanism to create account. 
 * Encryption
 * Etc.
 * 
 * This class reads the username and password from  "so called database.txt".
 * The file should have the format: "username,password".
 * 
 * @author Marc Conrad
 *
 */
public class LoginData {
	
	
	 
	boolean checkPassword(String username, String passwd) {
		try (BufferedReader br = new BufferedReader(new FileReader("so_called_database.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(passwd)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
