package utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Генератор страниц из шаблона
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class PageGenerator
{
	private static final String HTML_RESOURCE_DIR = "templates";
	private static PageGenerator pageGenerator;
	private final Configuration cfg;

	private PageGenerator() throws IOException
	{
		cfg = new Configuration();
		ClassLoader cl = this.getClass().getClassLoader();
		cfg.setDirectoryForTemplateLoading(new File(cl.getResource(HTML_RESOURCE_DIR).getFile()));
	}

	public static PageGenerator instance() throws IOException
	{
		if (pageGenerator == null)
			pageGenerator = new PageGenerator();
		return pageGenerator;
	}

	public String getPage(String filename, Map<String, Object> data)
	{
		Writer stream = new StringWriter();
		try
		{
			Template template = cfg.getTemplate(filename);
			template.process(data, stream);
		}
		catch (IOException | TemplateException e)
		{
			e.printStackTrace();
		}
		return stream.toString();
	}
}
