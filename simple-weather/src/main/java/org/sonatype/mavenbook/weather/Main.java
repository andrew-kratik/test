package org.sonatype.mavenbook.weather;

import java.io.InputStream;

import org.apache.log4j.PropertyConfigurator;

public class Main {
	private String zip;

	public Main(String zipcode) {
		this.zip = zipcode;
	}

	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure(Main.class.getClassLoader().getResource(
				"log4j.properties"));
		//default zip code: 60202
		String zipcode = "60202";
		try{
			zipcode = args[0];
		}catch (Exception e) {
			System.out.println("Zip code not provided. Using "+ zipcode);
		}
		new Main(zipcode).start();
	}

	private void start() throws Exception {
		//Retrieve data
		InputStream dataIn = new YahooRetriever().retrieve(zip);
		
		//Parse Data
		Weather weather = new YahooParser().parse(dataIn);
		
		//Format Data
		System.out.println(new WeatherFormatter().format(weather));
	}
}
