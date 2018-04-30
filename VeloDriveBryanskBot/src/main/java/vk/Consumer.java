package vk;

import com.vk.api.sdk.actions.Wall;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.wall.WallPostFull;

import java.util.ArrayList;
import java.util.List;

import static vk.Constants.*;

/**
 * Сборщик новостей из группы.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class Consumer {

    private static final VkApiClient VK_API_CLIENT = new VkApiClient(HttpTransportClient.getInstance());
    private static int START_TIME = getCurrentTime();

    /**
     * Получить список ссылок на новости, опубликованные после последней сборки.
     *
     * @return Список ссылок на новости
     * @throws ClientException Исключения ВК АПИ
     * @throws ApiException    Исключения ВК АПИ
     */
    @SuppressWarnings("unchecked")
    public static List<String> getNewPostsLinks() throws ClientException, ApiException {
        Wall w = new Wall(VK_API_CLIENT);
        UserActor u = new UserActor(USER_ID, ACCESS_TOKEN);

        List<WallPostFull> posts = (w.get(u).ownerId(OWNER_ID).execute().getItems());
        int newStartTime = getCurrentTime();

        List<String> resultList = new ArrayList();

        posts.stream()
                .filter(post -> post.getDate() >= START_TIME)
                .sorted((post1, post2) -> post1.getDate().compareTo(post2.getDate()))
                .forEach(post -> resultList.add(getPostUrl(post)));

        START_TIME = newStartTime;

        return resultList;
    }

    /**
     * Получить ссылку на пост
     *
     * @param post пост
     * @return ссылка на пост
     */
    private static String getPostUrl(WallPostFull post) {
        return POST_URL_PATTERN + OWNER_ID + "_" + post.getId();
    }

    /**
     * Получить текущее время
     *
     * @return текущее время
     */
    private static int getCurrentTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

}
