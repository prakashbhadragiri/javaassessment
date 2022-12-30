package com.assessments.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {

	/**
	 * This method is used to return file data as {@code String}
	 * @param fileName File should be located in src/main/resources
	 * @return file content as {@code String}
	 * @throws Exception in case of an error occurs in reading file.
	 */
	public static String readFromFile(String fileName) throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream is = classLoader.getResourceAsStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String line;
		StringBuilder builder = new StringBuilder();
		while ((line = br.readLine()) != null) {
			builder.append(line);
		}
		br.close();
		is.close();
		return builder.toString();
	}

}
