package lesson2.task1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;

/**
 * sax-parser
 */
public class MySaxHandler extends DefaultHandler {

	private static final String CLASSNAME = "class";
	private String element = null;
	private TestResource entity = new TestResource();

	public static void setFieldValue(Object object, String fieldName, String value) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);

			Types types = Types.getType(field.getType());
			switch (types) {
				case BYTE:
					field.set(object, Byte.valueOf(value));
					break;
				case BOOLEAN:
					field.set(object, Boolean.valueOf(value));
					break;
				case SHORT:
					field.set(object, Short.valueOf(value));
					break;
				case CHAR:
					field.set(object, value.charAt(0));
					break;
				case INT:
					field.set(object, Integer.decode(value));
					break;
				case FLOAT:
					field.set(object, Float.valueOf(value));
					break;
				case LONG:
					field.set(object, Long.valueOf(value));
					break;
				case DOUBLE:
					field.set(object, Double.valueOf(value));
					break;
				case STRING:
					field.set(object, value);
			}

			field.setAccessible(false);
		}
		catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public TestResource getResource() {
		return entity;
	}

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		element = null;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (!qName.equals(CLASSNAME)) {
			element = qName;
		}
		else {
			entity = new TestResource();
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (element != null) {
			String value = new String(ch, start, length);
			setFieldValue(entity, element, value);
		}
	}

	private enum Types {
		BYTE,
		BOOLEAN,
		SHORT,
		CHAR,
		INT,
		FLOAT,
		LONG,
		DOUBLE,
		STRING;

		public static Types getType(Class<?> clazz) {
			String className = clazz.getSimpleName().toUpperCase();
			return Types.valueOf(className);
		}
	}
}
