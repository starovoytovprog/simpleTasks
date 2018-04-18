package lesson6.task2;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализуйте метод, вычисляющий симметрическую разность двух множеств.
 * Метод должен возвращать результат в виде нового множества. Изменять переданные в него множества не допускается.
 *
 * @author Starovoytov
 * @since 16.04.2018
 */
public class SymmetricDifference
{
	@SuppressWarnings("unchecked")
	public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2)
	{
		Set result = new HashSet();

		for (T element : set1)
		{
			if (!set2.contains(element))
			{
				result.add(element);
			}
		}

		for (T element : set2)
		{
			if (!set1.contains(element))
			{
				result.add(element);
			}
		}

		return result;
	}
}
