package lesson6.task8;

import java.util.*;
import java.util.function.Consumer;

/**
 * Почтовый сервис
 *
 * @author Starovoytov
 * @since 18.04.2018
 */
@SuppressWarnings("unchecked")
public class MailService<T> implements Consumer<MailServiceAcceptable> {
	private final Map<String, List<T>> mailBox = new myHashMap<>();

	@Override
	public void accept(MailServiceAcceptable mailMessage) {
		String to = mailMessage.getTo();
		List<T> l;

		if (mailBox.get(to).equals(Collections.<String>emptyList())) {
			l = new ArrayList();
			mailBox.put(to, l);
		}
		else {
			l = mailBox.get(to);
		}

		l.add((T) mailMessage.getContent());
	}

	@Override
	public Consumer<MailServiceAcceptable> andThen(Consumer<? super MailServiceAcceptable> consumer) {
		return null;
	}

	public Map<String, List<T>> getMailBox() {
		return mailBox;
	}

	private class myHashMap<K, V> extends HashMap {
		@Override
		public V get(Object key) {
			Object value = super.get(key);

			if (value == null) {
				value = Collections.<String>emptyList();
			}

			return (V) value;
		}
	}
}
