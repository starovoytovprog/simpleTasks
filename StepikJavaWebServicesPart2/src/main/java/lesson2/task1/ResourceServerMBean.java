package lesson2.task1;

import org.xml.sax.SAXException;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Интерфейс для бина сервера ресурсов.
 *
 * @author Starovoytov
 * @since 29.01.2019
 */
public interface ResourceServerMBean {
	String getName();

	int getAge();

	void loadData(String path) throws ParserConfigurationException, SAXException, IOException;
}
