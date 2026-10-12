using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;

public class PanelMiniMission : SelfRegister2UIComponentsMonobehaviour
{
	private UIGrid m_grid;

	private List<RewardInfo> missionReward;

	private List<RewardInfo> exploitReward;

	private int commonRewardId;

	private bool m_isRankUpOccured;

	private int m_befUserRank;

	private bool m_isSeasonPassLvUpOccured;

	private int m_befSeasonPassLv;

	private int m_seasonPassLvUpCnt;

	public UIGrid grid
	{
		get
		{
			return null;
		}
	}

	protected override void Awake()
	{
	}

	private void Start()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void ShowMiniPanel()
	{
	}

	private void CheckUserRankupOccured()
	{
	}

	private void CheckSeasonPassLvUpOccured()
	{
	}

	private void OnUpdateMissions()
	{
	}

	private void InitMiniMission()
	{
	}

	private void InitMiniDailyMission(int count)
	{
	}

	private void AddMission(UserCommonMission mission)
	{
	}

	private void OnClickedComplete()
	{
	}

	private void OnUpdateComplete(int missionId, JsonData response)
	{
	}

	private void UpdateComplete(int missionId, List<RewardInfo> rewardSet, bool showDesc)
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateExploitReward(List<RewardInfo> rewardSet)
	{
		return null;
	}

	private void OnRewardClose()
	{
	}

	private void OnCompleteClose()
	{
	}

	private void ClearMiniMissionGrid()
	{
	}
}
