package lesson2.task7;

/**
 * Реализуйте метод, проверяющий, является ли заданная строка палиндромом. Палиндромом называется строка, которая читается одинаково слева направо и справа налево (в том числе пустая). При определении "палиндромности" строки должны учитываться только буквы и цифры. А пробелы, знаки препинания, а также регистр символов должны игнорироваться. Гарантируется, что в метод попадают только строки, состоящие из символов ASCII (цифры, латинские буквы, знаки препинания). Т.е. русских, китайских и прочих экзотических символов в строке не будет.
 * <p>
 * Sample Input: Madam, I'm Adam!; Sample Output: true
 *
 * @author Starovoytov
 * @since 04.04.2018
 */
public class IsPalindrome {
	/**
	 * Checks if given <code>text</code> is a palindrome.
	 *
	 * @param text any string
	 * @return <code>true</code> when <code>text</code> is a palindrome, <code>false</code> otherwise
	 */
	public static boolean isPalindrome(String text) {
		String letterOnly = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		return letterOnly.equals(new StringBuilder(letterOnly).reverse().toString());
	}
}
