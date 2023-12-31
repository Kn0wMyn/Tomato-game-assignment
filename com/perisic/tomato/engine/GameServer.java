package com.perisic.tomato.engine;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

import javax.imageio.ImageIO;
import java.net.HttpURLConnection;
import java.net.URI;

/**
 * Game that interfaces to an external Server to retrieve a game. 
 * A game consists of an image and an integer that denotes the solution of this game. 
 * 
 * @author Marc Conrad
 *
 */
public class GameServer {

	/**
	 * Basic utility method to read string for URL.
	 */

	private static String readUrl(String urlString)  {

			try {
				URI uri = new URI(urlString);
				URL url = uri.toURL();
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");  // pre existing code error here, dont ask how i fixed it...not even God knows.

				// ...

				InputStream inputStream = connection.getInputStream();
				ByteArrayOutputStream result = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int length;
				while ((length = inputStream.read(buffer)) != -1) {
					result.write(buffer, 0, length);
				}

			return result.toString("UTF-8");
		} catch (Exception e) {
			/* To do: proper exception handling when URL cannot be read. */
			System.out.println("An Error occurred: " + e.toString());
			e.printStackTrace();
			return null;
			} finally {
				// Add any necessary cleanup or resource release code here
			}
	}

	/**
	 * Retrieves a random game from the web site.
	 * @return a random game or null if a game cannot be found. 
	 */
	public Game getRandomGame() {
		// See http://marconrad.com/uob/tomato for details of usage of the api. 
		
		String tomatoapi = "https://marcconrad.com/uob/tomato/api.php?out=csv&base64=yes";
		String dataraw = readUrl(tomatoapi);
		String[] data = dataraw.split(",");

		byte[] decodeImg = Base64.getDecoder().decode(data[0]);
		ByteArrayInputStream quest = new ByteArrayInputStream(decodeImg);

		int solution = Integer.parseInt(data[1]);

		BufferedImage img = null;
		try {
			img = ImageIO.read(quest);
			return new Game(img, solution);
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
	}

}
