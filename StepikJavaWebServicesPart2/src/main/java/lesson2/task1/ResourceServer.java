package lesson2.task1;

import org.xml.sax.SAXException;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Ресурсный сервер
 *
 * @author Starovoytov
 * @since 29.01.2019
 */
public class ResourceServer implements ResourceServerMBean {
	private TestResource testResource = new TestResource();

	@Override
	public String getName() {
		return testResource.getName();
	}

	@Override
	public int getAge() {
		return testResource.getAge();
	}

	@Override
	public void loadData(String path) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		MySaxHandler defaultHandler = new MySaxHandler();
		saxParser.parse(path, defaultHandler);
		testResource = defaultHandler.getResource();
	}
}
