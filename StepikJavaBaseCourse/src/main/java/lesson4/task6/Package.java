package lesson4.task6;

/**
 * Класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность.
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public class Package {
	private final String content;
	private final int price;

	public Package(String content, int price) {
		this.content = content;
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Package aPackage = (Package) o;

		if (price != aPackage.price) return false;
		if (!content.equals(aPackage.content)) return false;

		return true;
	}
}
