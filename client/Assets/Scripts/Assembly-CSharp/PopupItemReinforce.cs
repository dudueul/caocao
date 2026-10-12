using System.Collections;
using System.Diagnostics;

public class PopupItemReinforce : PanelPopup2Buttons
{
	private int m_gaugeTo;

	private int m_gaugeBeforMax;

	private UserItem m_userItem;

	private bool m_wait;

	private ulong m_unitSrl;

	private int paidHonor;

	public static void ShowPopup(UserItem userItem, ulong unitSrl)
	{
	}

	protected override void OnSetContext()
	{
	}

	public void Apply(UserItem userItem, ulong unitSrl)
	{
	}

	private void ReinforceItem()
	{
	}

	public void OnCompleteProgressToMax()
	{
	}

	[DebuggerHidden]
	private IEnumerator Waiting()
	{
		return null;
	}

	public void OnCompleteProgressToNextExp()
	{
	}
}
