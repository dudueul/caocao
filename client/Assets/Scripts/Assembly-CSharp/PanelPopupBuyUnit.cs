using System;

public class PanelPopupBuyUnit : PanelPopup2Buttons
{
	private int m_unitTreeNodeId;

	private Action m_action;

	public static void Show(int unitTreeNodeId, Action action)
	{
	}

	protected override void OnSetContext()
	{
	}

	public void Apply(int unitTreeNodeId, Action action)
	{
	}
}
