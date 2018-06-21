package processors;

import document.Waybill;
import loader.StringLoader;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование SimpleProcessor {@link SimpleProcessor}
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class SimpleProcessorTest {

	private static final String SIMPLE_TEST_STRING = "1;1" + System.lineSeparator() + "1;1" + System.lineSeparator() + "1;2" + System.lineSeparator() + "1;3" + System.lineSeparator() + "1;4" + System.lineSeparator() + "1;5" + System.lineSeparator() + "1;6" + System.lineSeparator() + "1;7" + System.lineSeparator() + "1;8" + System.lineSeparator() + "1;9" + System.lineSeparator();

	private static final String REAL_TASK_TEST_STRING = "149430;1498;1498;1498;1498;1320;1320;3926;3926;3926;2648;4764;5020;5020;996;996;396;396;1100;1100;1100;2120;2120;2120;2120;2065;2065;2065;2065;2065;2065;2065;2065;1945;1945;1945;1945;1945;1945;1945;1945;284;284;284;284;284;284;284;284;284;284;284;284;284;284;2730;57;57;57;57;57;57;57;57;57;57;57;57;57;57;57;57;79;79;79;79;79;79;79;79;79;79;79;79;79;79;79;79;79;79;79;79;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;85;5063;1020;1020";

	/**
	 * Проверка простого поиска
	 */
	@Test
	public void simpleFindTest() {
		Waybill waybill = StringLoader.load(SIMPLE_TEST_STRING);
		Waybill waybillResult = SimpleProcessor.simpleFind(waybill, 10);
		assertEquals(waybillResult.getSum(), 10);
	}

	/**
	 * Проверка примера реальной задачи
	 */
	@Test
	@Ignore
	public void realFindTest() {
		Waybill waybill = StringLoader.loadSumOnly(REAL_TASK_TEST_STRING);
		Waybill waybillResult = SimpleProcessor.simpleFind(waybill, 200_000);
		assertEquals(waybillResult.getSum(), 200_000);

		System.out.println(waybillResult);
	}
}
