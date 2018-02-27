package vm;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static vm.Constants.COMMAND_LINE_DELIMITER;

/**
 * Тестирование виртуальной машины {@link VirtualMaschine}
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public class VirtualMaschineTest {
    private static final VirtualMaschine virtualMaschine = new VirtualMaschine();

    /**
     * Тестируется простой вывод значения из стека
     *
     * @throws Exception ошибка в машинном коде
     */
    @Test
    public void simpleStackValueOut() throws Exception {
        String machineCode = "PUSH 15" + COMMAND_LINE_DELIMITER +
                "ECHO";
        String expectedResult = "15";

        testExecute(machineCode, expectedResult);
    }

    /**
     * Тестируется сложение двух чисел в стэке
     *
     * @throws Exception ошибка в машинном коде
     */
    @Test
    public void simpleStackAdd() throws Exception {
        String machineCode = "PUSH 2" + COMMAND_LINE_DELIMITER +
                "PUSH 7" + COMMAND_LINE_DELIMITER +
                "ADD" + COMMAND_LINE_DELIMITER +
                "ECHO";
        String expectedResult = "9";

        testExecute(machineCode, expectedResult);
    }

    /**
     * Тестируется вычитание двух чисел в стэке
     *
     * @throws Exception ошибка в машинном коде
     */
    @Test
    public void simpleStackSub() throws Exception {
        String machineCode = "PUSH 2" + COMMAND_LINE_DELIMITER +
                "PUSH 7" + COMMAND_LINE_DELIMITER +
                "SUB" + COMMAND_LINE_DELIMITER +
                "ECHO";
        String expectedResult = "5";

        testExecute(machineCode, expectedResult);
    }

    /**
     * Тестируется работа с переменными
     *
     * @throws Exception ошибка в машинном коде
     */
    @Test
    public void simpleStackVariables() throws Exception {
        String machineCode = "PUSH 5" + COMMAND_LINE_DELIMITER +
                "STORE var1" + COMMAND_LINE_DELIMITER +
                "PUSH 10" + COMMAND_LINE_DELIMITER +
                "STORE var2" + COMMAND_LINE_DELIMITER +
                "FETCH var2" + COMMAND_LINE_DELIMITER +
                "FETCH var1" + COMMAND_LINE_DELIMITER +
                "SUB" + COMMAND_LINE_DELIMITER +
                "ECHO";
        String expectedResult = "-5";

        testExecute(machineCode, expectedResult);
    }

    /**
     * Тестируется выход из приложения
     *
     * @throws Exception ошибка в машинном коде
     */
    @Test
    public void simpleStackHalt() throws Exception {
        String machineCode = "PUSH 5" + COMMAND_LINE_DELIMITER +
                "HALT" + COMMAND_LINE_DELIMITER +
                "ECHO";
        String expectedResult = "";

        testExecute(machineCode, expectedResult);
    }

    /**
     * Тестируется удаление значения из стека
     *
     * @throws Exception ошибка в машинном коде
     */
    @Test
    public void simpleStackPop() throws Exception {
        String machineCode = "PUSH 5" + COMMAND_LINE_DELIMITER +
                "PUSH 10" + COMMAND_LINE_DELIMITER +
                "POP" + COMMAND_LINE_DELIMITER +
                "ECHO";
        String expectedResult = "5";

        testExecute(machineCode, expectedResult);
    }

    /**
     * Тестируется переход к команде
     *
     * @throws Exception ошибка в машинном коде
     */
    @Test
    public void simpleStackJmp() throws Exception {
        String machineCode = "JMP 5" + COMMAND_LINE_DELIMITER +
                "PUSH 1" + COMMAND_LINE_DELIMITER +
                "ECHO" + COMMAND_LINE_DELIMITER +
                "HALT" + COMMAND_LINE_DELIMITER +
                "PUSH 2" + COMMAND_LINE_DELIMITER +
                "ECHO";
        String expectedResult = "2";

        testExecute(machineCode, expectedResult);
    }

    /**
     * Тестируется условие
     *
     * @throws Exception ошибка в машинном коде
     */
    @Test
    public void simpleStackLt() throws Exception {
        String machineCode = "PUSH 2" + COMMAND_LINE_DELIMITER +
                "PUSH 1" + COMMAND_LINE_DELIMITER +
                "LT" + COMMAND_LINE_DELIMITER +
                "ECHO";
        String expectedResult = "1";
        testExecute(machineCode, expectedResult);

        machineCode = "PUSH 1" + COMMAND_LINE_DELIMITER +
                "PUSH 1" + COMMAND_LINE_DELIMITER +
                "LT" + COMMAND_LINE_DELIMITER +
                "ECHO";
        expectedResult = "0";
        testExecute(machineCode, expectedResult);

        machineCode = "PUSH 1" + COMMAND_LINE_DELIMITER +
                "PUSH 2" + COMMAND_LINE_DELIMITER +
                "LT" + COMMAND_LINE_DELIMITER +
                "ECHO";
        expectedResult = "0";

        testExecute(machineCode, expectedResult);
    }

    /**
     * Тестируется условный переход к команде если 0
     *
     * @throws Exception ошибка в машинном коде
     */
    @Test
    public void simpleStackJz() throws Exception {
        String machineCode = "PUSH 0" + COMMAND_LINE_DELIMITER +
                "JZ 6" + COMMAND_LINE_DELIMITER +
                "PUSH 1" + COMMAND_LINE_DELIMITER +
                "ECHO" + COMMAND_LINE_DELIMITER +
                "HALT" + COMMAND_LINE_DELIMITER +
                "PUSH 2" + COMMAND_LINE_DELIMITER +
                "ECHO";
        String expectedResult = "2";

        testExecute(machineCode, expectedResult);

        machineCode = "PUSH 1" + COMMAND_LINE_DELIMITER +
                "JZ 6" + COMMAND_LINE_DELIMITER +
                "PUSH 1" + COMMAND_LINE_DELIMITER +
                "ECHO" + COMMAND_LINE_DELIMITER +
                "HALT" + COMMAND_LINE_DELIMITER +
                "PUSH 2" + COMMAND_LINE_DELIMITER +
                "ECHO";
        expectedResult = "1";

        testExecute(machineCode, expectedResult);
    }

    /**
     * Тестируется условный переход к команде если 0
     *
     * @throws Exception ошибка в машинном коде
     */
    @Test
    public void simpleStackJnz() throws Exception {
        String machineCode = "PUSH 0" + COMMAND_LINE_DELIMITER +
                "JNZ 6" + COMMAND_LINE_DELIMITER +
                "PUSH 1" + COMMAND_LINE_DELIMITER +
                "ECHO" + COMMAND_LINE_DELIMITER +
                "HALT" + COMMAND_LINE_DELIMITER +
                "PUSH 2" + COMMAND_LINE_DELIMITER +
                "ECHO";
        String expectedResult = "1";

        testExecute(machineCode, expectedResult);

        machineCode = "PUSH 1" + COMMAND_LINE_DELIMITER +
                "JNZ 6" + COMMAND_LINE_DELIMITER +
                "PUSH 1" + COMMAND_LINE_DELIMITER +
                "ECHO" + COMMAND_LINE_DELIMITER +
                "HALT" + COMMAND_LINE_DELIMITER +
                "PUSH 2" + COMMAND_LINE_DELIMITER +
                "ECHO";
        expectedResult = "2";

        testExecute(machineCode, expectedResult);
    }

    /**
     * Выполняет машинный код и сравнивает с ожидаемым результатом.
     *
     * @param mashineCode    исполняемый код
     * @param expectedResult ожидаемый результат
     * @throws Exception исключение выполнения
     */
    private void testExecute(String mashineCode, String expectedResult) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        virtualMaschine.run(mashineCode);
        System.out.flush();
        System.setOut(old);
        assertEquals(baos.toString(), expectedResult + (expectedResult.isEmpty() ? "" : "\r\n"));
    }
}
