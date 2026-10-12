using System.Collections.Generic;
using LitJson;

public class PanelDailyCommonMission : CaocaoUIModalPopup
{
	private int requestCompleteId;

	private UIGrid m_gridTop;

	private UIGrid m_gridDaily;

	private int topMissionId;

	private int m_befSeasonPassLv;

	private UIGrid gridTop
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridDaily
	{
		get
		{
			return null;
		}
	}

	public static void Show()
	{
	}

	private void Start()
	{
	}

	private void OnResetDailyMissions()
	{
	}

	private void OnGetSeasonPassCashShopInfo()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void OnClickComplete(int missionId)
	{
	}

	public void OnClickTopMissionComplete()
	{
	}

	private void OnCompleteMissionCallback(JsonData response)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateResetMission()
	{
	}

	private void UpdateTopMission()
	{
	}

	private void UpdateDailyMissions()
	{
	}

	private void UpdateTopMissionItems(CommonMission mission)
	{
	}

	private void AddTopMIssionReward(RewardInfo info)
	{
	}

	private void AddDailyMissionItem(UserCommonMission userMission)
	{
	}

	private void ClearGrid(UIGrid grid)
	{
	}

	private void ShowFinishMsg()
	{
	}

	private void UpdateComplete(int missionId, List<RewardInfo> rewardSet, bool showDesc)
	{
	}

	private UserCommonMission GetUserCommonMissionForTopStanding()
	{
		return null;
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
