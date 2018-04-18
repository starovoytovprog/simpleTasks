package lesson5.task5;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson5.task5.Animal {@link Animal}
 *
 * @author Starovoytov
 * @since 13.04.2018
 */
public class AnimalTest
{
	/**
	 * Проверка результата выполнения метода
	 * @throws IOException Исключения в InputStream/OutputStream
	 */
	@Test
	public void testRun() throws IOException
	{
		Animal[] animals = new Animal[3];
		animals[0] = new Animal("Заяц");
		animals[1] = new Animal("Зёбра");
		animals[2] = new Animal("Черепаха");
		byte[] byteAnimals;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ObjectOutputStream oos = new ObjectOutputStream(baos))
		{
			oos.writeInt(3);
			for (Animal animal : animals)
			{
				oos.writeObject(animal);
			}
			oos.flush();

			byteAnimals = baos.toByteArray();
		}

		Animal[] resultAnimals = Animal.deserializeAnimalArray(byteAnimals);
		assertTrue(Arrays.equals(animals, resultAnimals));
	}
}
