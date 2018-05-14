package lesson5.task5;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 * Дан сериализуемый класс Animal.
 * Реализуйте метод, который из переданного массива байт восстановит массив объектов Animal. Массив байт устроен следующим образом. Сначала идет число типа int, записанное при помощи ObjectOutputStream.writeInt(size). Далее подряд записано указанное количество объектов типа Animal, сериализованных при помощи ObjectOutputStream.writeObject(animal).
 * Если вдруг массив байт не является корректным представлением массива экземпляров Animal, то метод должен бросить исключение java.lang.IllegalArgumentException.
 * Причины некорректности могут быть разные. Попробуйте подать на вход методу разные некорректные данные и посмотрите, какие исключения будут возникать. Вот их-то и нужно превратить в IllegalArgumentException и выбросить. Если что-то забудете, то проверяющая система подскажет. Главное не глотать никаких исключений, т.е. не оставлять нигде пустой catch.
 *
 * @author Starovoytov
 * @since 13.04.2018
 */
public class Animal implements Serializable {
	private final String name;

	public Animal(String name) {
		this.name = name;
	}

	public static Animal[] deserializeAnimalArray(byte[] data) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				int count = ois.readInt();
				Animal[] animals = new Animal[count];

				for (int i = 0; i < count; i++) {
					animals[i] = (Animal) ois.readObject();
				}

				return animals;
			}
		}
		catch (Throwable t) {
			throw new IllegalArgumentException(t);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Animal) {
			return Objects.equals(name, ((Animal) obj).name);
		}
		return false;
	}
}
