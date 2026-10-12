using System.Collections.Generic;
using GameFlow;

public abstract class GameFlowNodeBase
{
	protected NodeType m_nodeType;

	protected List<NodeType> m_followers;

	public GameFlowNodeBase()
	{
	}

	protected abstract void _DoLogic();

	protected abstract void DefineFollowers();

	protected abstract int? _GetState();

	public void DoLogic()
	{
	}

	public bool isNodeType(NodeType nodeType)
	{
		return false;
	}

	public NodeType GetNodeType()
	{
		return NodeType.NONE;
	}

	public void CallFollowerByNodeType(NodeType nodeType)
	{
	}

	public int? GetState()
	{
		return null;
	}
}
