using System;
using System.Collections.Generic;

public class PopupMissionComplete : CaocaoUIModalPopup
{
	public Action OnComplete;

	public void UpdateMissionUI(CommonMission mission, List<RewardInfo> rewardSet, bool showDesc)
	{
	}

	public void UpdateAchievementUI(Achievement achievement, List<RewardInfo> rewardSet)
	{
	}

	public void UpdateExploitRewardUI(int commonRewardId, List<RewardInfo> rewardSet)
	{
	}

	private string GetExploitIcon(int commonRewardId)
	{
		return null;
	}

	private void SetIcon(string iconName)
	{
	}

	public void OnClickComplete()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
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
