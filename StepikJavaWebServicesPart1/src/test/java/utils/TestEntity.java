package utils;

import javax.persistence.*;

/**
 * Тестовая сущность
 *
 * @author Starovoytov
 * @since 31.05.2018
 */
@Entity
@Table(name = "test_entity")
public class TestEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "data")
	private String data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
