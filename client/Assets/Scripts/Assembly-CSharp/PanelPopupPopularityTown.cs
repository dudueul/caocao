using LitJson;
using UnityEngine;

public class PanelPopupPopularityTown : PanelPopupPopularityBase
{
	[SerializeField]
	private UILabelLocalized m_popularityValue;

	[SerializeField]
	private UILabelLocalized m_townName;

	[SerializeField]
	private UILabelLocalized m_standardOfReward;

	[SerializeField]
	private UILabelLocalized m_remainReward;

	[SerializeField]
	private GameObject m_rewardObj;

	[SerializeField]
	private UIButton m_receiveBtn;

	public override void Receive()
	{
	}

	protected override void UpdateUI()
	{
	}

	private void OnReceivePopularityReward(JsonData response)
	{
	}

	private void UpdateReward()
	{
	}

	public static void Show()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}
}
