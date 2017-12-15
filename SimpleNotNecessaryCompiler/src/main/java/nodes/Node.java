package nodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Узел лексического дерева
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public class Node
{
	private NodeType type;
	private List<Node> dependentNodes = new ArrayList<>();
	private String value = "";

	/**
	 * Получить значение ноды
	 *
	 * @return значение ноды
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Установить значение ноды
	 *
	 * @param value значение ноды
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * Добавить зависимую ноду
	 *
	 * @param dependentNode зависимая нода
	 */
	public void addDependentNode(Node dependentNode)
	{
		dependentNodes.add(dependentNode);
	}

	/**
	 * Получить зависимую ноду
	 *
	 * @param i индекс ноды в списке
	 * @return зависимая нода
	 */
	public Node getDependentNode(int i)
	{
		if (i >= dependentNodes.size())
		{
			return null;
		}

		return dependentNodes.get(i);
	}

	/**
	 * Получить все зависимые ноды
	 *
	 * @return зависимые ноды
	 */
	public List<Node> getDependentNodes()
	{
		return dependentNodes;
	}

	/**
	 * Получить тип ноды
	 *
	 * @return тип ноды
	 */
	public NodeType getType()
	{
		return type;
	}

	/**
	 * Установить тип ноды
	 *
	 * @param type тип ноды
	 */
	public void setType(NodeType type)
	{
		this.type = type;
	}

	/**
	 * Выполнение сравнения нод
	 *
	 * @param node нода, с которой сравнивается текущая
	 * @return true, если ноды идентичны
	 */
	@Override
	public boolean equals(Object node)
	{
		if (node instanceof Node)
		{
			return type == ((Node) node).type
				&& value.equals(((Node) node).value)
				&& dependentNodes.containsAll(((Node) node).dependentNodes)
				&& dependentNodes.size() == ((Node) node).dependentNodes.size();
		}

		return false;
	}
}
