package lesson4.task6;

import org.junit.Test;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static lesson4.task6.Constants.AUSTIN_POWERS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson4.task6.Spy {@link Spy}
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public class SpyTest {
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun() {
		Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
		logger.setUseParentHandlers(false);
		MyLogHandler h = new MyLogHandler();
		logger.addHandler(h);

		MailService spy = new Spy(logger);

		spy.processMail(new MailMessage("form", "to", "message"));
		assertEquals(h.getLogRecord().getLevel(), Level.INFO);
		assertEquals(h.getLogRecord().getMessage(), "Usual correspondence: from form to to");
		h.close();

		spy.processMail(new MailMessage(AUSTIN_POWERS, "to", "message"));
		assertEquals(h.getLogRecord().getLevel(), Level.WARNING);
		assertEquals(h.getLogRecord().getMessage(), "Detected target mail correspondence: from Austin Powers to to \"message\"");
		h.close();

		spy.processMail(new MailMessage("from", AUSTIN_POWERS, "message"));
		assertEquals(h.getLogRecord().getLevel(), Level.WARNING);
		assertEquals(h.getLogRecord().getMessage(), "Detected target mail correspondence: from from to Austin Powers \"message\"");
		h.close();

		spy.processMail(new MailPackage("from", AUSTIN_POWERS, new Package("pack", 100)));
		assertTrue(h.getLogRecord() == null);
	}

	private class MyLogHandler extends Handler {

		private LogRecord logRecord;

		@Override
		public void publish(LogRecord logRecord) {
			this.logRecord = logRecord;
		}

		@Override
		public void flush() {
		}

		@Override
		public void close() throws SecurityException {
			logRecord = null;
		}

		public LogRecord getLogRecord() {
			return logRecord;
		}
	}
}
