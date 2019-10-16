package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import task.crawl.Crawl;

public final class PropertiesUtils {

	public static Properties loadProperties(String propertiesFilename) {
		Properties prop = new Properties();

		try (InputStream input = Crawl.class.getClassLoader().getResourceAsStream(propertiesFilename)) {

			if (input == null) {
				System.err.println("Unable to find " + propertiesFilename + " file ");
			}

			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}

}
