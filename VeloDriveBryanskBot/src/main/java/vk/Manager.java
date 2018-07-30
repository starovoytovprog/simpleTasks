package vk;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiTooManyException;
import com.vk.api.sdk.exceptions.ClientException;
import message.MessageConsumer;
import message.MessageManager;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static vk.Constants.*;

/**
 * Управление сборкой новостей.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class Manager {

	private static final List<Integer> OWNERS = new ArrayList<>();
	private static MessageConsumer CONSUMER;
	private static Thread scanThread = null;
	private static int CURRENT = 0;

	static {
		Arrays.stream(OWNER_ID.split(OWNER_DELIMITER)).forEach(id -> OWNERS.add(Integer.parseInt(id)));
	}

	/**
	 * Запустить поток-обработчик новостей.
	 *
	 * @throws TelegramApiRequestException ошибка старта
	 */
	public static void start() throws TelegramApiRequestException {
		CONSUMER = MessageManager.getInstance();
		Collector collector = new Collector();

		scanThread = new Thread(() -> {
			while (true) {
				scan(collector);
				delay();
			}
		});

		scanThread.start();
	}

	/**
	 * Обработка последних новостей
	 */
	private static void scan(Collector collector) {
		try {
			List<String> linkList = collector.getNewPostsLinks(getOwnerId());
			linkList.stream().forEach(link -> {
				System.out.println("New post in group!");
				CONSUMER.messageProcess("Новый пост в группе!\n" + link, true);
				delay();
			});
		}
		catch (ApiTooManyException e) {
			e.printStackTrace();
			delayTime(DELAY_FOR_ERROR);
		}
		catch (ClientException | ApiException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Получить следующий id из списка
	 *
	 * @return
	 */
	private static int getOwnerId() {
		CURRENT = (CURRENT + 1)%OWNERS.size();
		return OWNERS.get(CURRENT);
	}

	/**
	 * Ожидание между выполнениями запросов к API
	 */
	private static void delay() {
		delayTime(DELAY);
	}

	/**
	 * Ожидание указанного времени
	 */
	private static void delayTime(long delay) {
		try {
			Thread.currentThread().sleep(delay);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
