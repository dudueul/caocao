using System.Collections.Generic;
using LitJson;

public class TimeBoxOpenDirection : CaocaoUIModalPopup
{
	public TimeBoxRewardItemUI item1UI;

	public TimeBoxRewardItemUI item2UI;

	public TimeBoxOpenBasicReward m_popupBasicReward;

	private bool item1Clicked;

	private bool item2Clicked;

	private TimeBoxRewardItemUI selectedBox;

	private UserTimeBox m_userTimeBox;

	private PlayMakerFSM fsm
	{
		get
		{
			return null;
		}
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public static void Show(UserTimeBox userTimeBox)
	{
	}

	public static void Show(List<RewardInfo> rewards)
	{
	}

	private void SetBoxEffect()
	{
	}

	private void OnItemClicked(TimeBoxRewardItemUI ui)
	{
	}

	public void DecideBox()
	{
	}

	private void OnDecideBox(JsonData response)
	{
	}
}
