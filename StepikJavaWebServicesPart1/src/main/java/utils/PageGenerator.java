package utils;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
	private static String TEMP_DIR = System.getProperty("user.home") + File.separator + ".stepicJavaWebServices1Temp" + File.separator;
	private static PageGenerator pageGenerator;
	private final Configuration cfg;

	private PageGenerator() throws IOException
	{
		cfg = new Configuration();
		cfg.setTemplateLoader(new TempFileTemplateLoader());
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

	private class TempFileTemplateLoader implements TemplateLoader
	{
		@Override
		public Object findTemplateSource(String name) throws IOException
		{
			String sourceName = HTML_RESOURCE_DIR + "/" + name;
			InputStream is = PageGenerator.class.getClassLoader().getResourceAsStream(sourceName);
			if (is != null)
			{
				File tempFile = new File(TEMP_DIR + sourceName);

				File destFolder = tempFile.getParentFile();
				if (!destFolder.exists())
				{
					destFolder.mkdirs();
				}

				Files.copy(is, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

				return tempFile;
			}

			return null;
		}

		@Override
		public long getLastModified(Object templateSource)
		{
			return ((File) templateSource).lastModified();
		}

		@Override
		public Reader getReader(Object templateSource, String encoding) throws IOException
		{
			return new FileReader((File) templateSource);
		}

		@Override
		public void closeTemplateSource(Object templateSource) throws IOException
		{
			//((File) templateSource).delete();
		}
	}
}
