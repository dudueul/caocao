using System.Collections.Generic;
using GameFlow;

public class GameFlowController : Singleton<GameFlowController>
{
	private Dictionary<NodeType, GameFlowNodeBase> m_name2gameFlows;

	public NodeType lastGameFlow;

	public LastGameMode lastGameMode
	{
		get
		{
			return LastGameMode.None;
		}
		set
		{
		}
	}

	protected override void Awake()
	{
	}

	public void QuitApplication()
	{
	}

	public GameFlowNodeBase GetGameFlowNode(NodeType nodeType)
	{
		return null;
	}
}
