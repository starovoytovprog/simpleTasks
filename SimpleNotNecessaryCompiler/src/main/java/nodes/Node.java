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

	public void setType(NodeType type)
	{
		this.type = type;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public void addDependentNode(Node dependentNode)
	{
		dependentNodes.add(dependentNode);
	}

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
