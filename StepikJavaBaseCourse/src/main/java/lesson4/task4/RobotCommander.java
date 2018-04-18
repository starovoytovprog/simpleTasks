package lesson4.task4;

/**
 * Вспомним нашего старого знакомого робота. Теперь мы будем давать роботу команды удаленно, подключаясь к нему по беспроводному каналу связи.
 * Ваша задача — реализовать метод который устанавливает соединение с роботом, отдает ему команду на перемещение в заданную точку и затем закрывает соединение, выполняя при необходимости повтор этой последовательности до трех раз.
 * При этом:
 * 1) Попытка отдать команду роботу считается успешной, если удалось установить соединение и выполнить метод RobotConnection.moveRobotTo() без исключений. Ошибка закрытия соединения не важна и должна игнорироваться.
 * 2) Если первая попытка подключиться и отдать команду оказалась неуспешной, то закрываем соединение и выполняем вторую попытку. Если вторая тоже не удалась, то выполняется третья. После третьей неудачи метод должен бросить исключение RobotConnectionException.
 * 3) Метод отвечает за открытие и закрытие соединения. Если соединение удалось установить, то оно обязательно должно быть закрыто несмотря на возможные исключения, случившиеся в дальнейшем во время работы метода.
 * 4) Если во время работы метода случилось исключение любого типа, отличного от RobotConnectionException, метод должен завершиться и выбросить это исключение, не выполняя повторных попыток пообщаться с роботом. Единственное, что метод должен сделать перед выбросом этого исключения — закрыть открытое соединение RobotConnection.
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public class RobotCommander {
    private static int count;
    private static int catch_count;

    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        count = 0;
        catch_count = 0;
        boolean isMove = false;

        int errorCount = 3;
        for (int i = 0; i < errorCount; i++) {
            count++;
            try (RobotConnection connection = robotConnectionManager.getConnection()) {
                connection.moveRobotTo(toX, toY);
                isMove = true;
                return;
            } catch (RobotConnectionException ex) {
                if (isMove) {
                    return;
                }

                catch_count++;
                if (i == errorCount - 1)
                    throw ex;
            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    public static int getCount() {
        return count;
    }

    public static int getCatch_count() {
        return catch_count;
    }
}
