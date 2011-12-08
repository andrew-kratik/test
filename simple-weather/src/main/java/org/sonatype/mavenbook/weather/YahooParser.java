package org.sonatype.mavenbook.weather;

import java.io.InputStream;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;

public class YahooParser {
	private static Logger log = Logger.getLogger(YahooParser.class);
	
	public Weather parse(InputStream dataIn) throws Exception {
		Weather weather = new Weather();
		
		log.info("Creating XML Reader");
		SAXReader xmlReader = createXMLReader();
		Document doc = xmlReader.read(dataIn);
		log.info("Parsing file:" + doc.asXML());
		
		log.info("Parsing XML response");
		weather.setCity(doc.valueOf("/rss/channel/y:location/@city"));
		weather.setRegion(doc.valueOf("/rss/channel/y:location/@region"));
		weather.setCountry(doc.valueOf("/rss/channel/y:location/@country"));
		weather.setCondition(doc.valueOf("/rss/channel/item/y:condition/@text"));
		weather.setTemp(doc.valueOf("/rss/channel/item/y:condition/@temp"));
		weather.setChill(doc.valueOf("/rss/channel/y:wind/@chill"));
		weather.setHumidity(doc.valueOf("/rss/channel/y:atmosphere/@humidity"));
		log.info("Parsing XML response finished");
		return weather;
	}

	private SAXReader createXMLReader() {
		Map<String, String> uris = new HashedMap();
		uris.put("y","http://xml.weather.yahoo.com/ns/rss/1.0");
		DocumentFactory factory = new DocumentFactory();
		factory.setXPathNamespaceURIs(uris);
		SAXReader xmlReader = new SAXReader();
		xmlReader.setDocumentFactory(factory);
		return xmlReader;
	}

}
