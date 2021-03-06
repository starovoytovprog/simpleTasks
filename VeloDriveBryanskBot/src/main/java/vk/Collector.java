package vk;

import com.vk.api.sdk.actions.Wall;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.wall.WallPostFull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static vk.Constants.*;

/**
 * Сборщик новостей из группы.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class Collector {
	private int startTime = getCurrentTime();
	private Map<Integer, Integer> timeMap = new HashMap<>();

	public Collector() {
		new Collector(getCurrentTime());
	}

	public Collector(int startTime) {
		this.startTime = startTime;
	}

	/**
	 * Получить ссылку на пост
	 *
	 * @param post пост
	 * @return ссылка на пост
	 */
	private static String getPostUrl(WallPostFull post) {
		return POST_URL_PATTERN + post.getOwnerId() + "_" + post.getId();
	}

	/**
	 * Получить текущее время
	 *
	 * @return текущее время
	 */
	private static int getCurrentTime() {
		return (int) (System.currentTimeMillis()/1000);
	}

	/**
	 * Получить список ссылок на новости, опубликованные после последней сборки.
	 *
	 * @param ownerId id просматриваемой группы
	 * @return Список ссылок на новости
	 * @throws ClientException Исключения ВК АПИ
	 * @throws ApiException    Исключения ВК АПИ
	 */
	@SuppressWarnings("unchecked")
	public List<String> getNewPostsLinks(int ownerId) throws ClientException, ApiException {
		final Integer actualCurrentTime = timeMap.get(ownerId) == null ? startTime : timeMap.get(ownerId);
		Wall w = new Wall(new VkApiClient(HttpTransportClient.getInstance()));
		UserActor u = new UserActor(USER_ID, ACCESS_TOKEN);

		List<WallPostFull> posts = (w.get(u).ownerId(ownerId).execute().getItems());
		timeMap.put(ownerId, getCurrentTime());
		List<String> resultList = new ArrayList();
		posts.stream().filter(post -> post.getDate() >= actualCurrentTime).sorted((post1, post2) -> post1.getDate().compareTo(post2.getDate())).forEach(post -> resultList.add(getPostUrl(post)));
		
		return resultList;
	}
}
