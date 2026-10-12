using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class FinishCompetition : FinishBase
{
	[SerializeField]
	private Transform m_itemGridUI;

	private UIButton m_btnClose;

	private Tier m_previousTier;

	[SerializeField]
	private GameObject boxObj;

	[SerializeField]
	private UITexture boxIcon;

	[SerializeField]
	private UILabelLocalized boxName;

	private TimeBox m_rewardTimeBox;

	[SerializeField]
	private GameObject hotTimeBoxBonusObj;

	[SerializeField]
	private UILabelLocalized hotTimeNoticeLabel;

	[SerializeField]
	private UILabelLocalized hotTimeRewardLabel;

	[SerializeField]
	private UILabelLocalized hotTimeBonusScoreLabel;

	private void Start()
	{
	}

	public void OnInit(Transform parent)
	{
	}

	protected override void OnDestroy()
	{
	}

	public override void ShowFinish()
	{
	}

	public void UpdateChildUIStatus()
	{
	}

	public void UpdatePanelUI()
	{
	}

	[DebuggerHidden]
	private IEnumerator SendCompetitionEnd(int point, bool isWin)
	{
		return null;
	}

	private void OnProcessBattleEnd(JsonData response)
	{
	}

	protected override void ChangeScene(string sceneName, bool autoDestroy = true)
	{
	}

	private void OnEventLoopEnded()
	{
	}

	protected override void GoToWorldMap()
	{
	}

	[DebuggerHidden]
	private IEnumerator OnPostEffect(int addUserExp)
	{
		return null;
	}

	[DebuggerHidden]
	protected override IEnumerator ShowPedlarInstanceEffect()
	{
		return null;
	}

	private void SetButtonsEnable(bool isEnable)
	{
	}

	private void UpdateCompetitionInfo(JsonData response)
	{
	}

	private void UpdateOpponentInfo(int opponentDeltaPoint)
	{
	}

	private string GetResultRankPercent(int curRank, int rankCnt)
	{
		return null;
	}

	private void UpdateFriendlyCompetitionInfo()
	{
	}

	private void UpdateRewardTimeBox()
	{
	}

	private void UpdateGuildMissions()
	{
	}

	private void UpdateCompetitionAchievement()
	{
	}

	public void OnBtnProfile()
	{
	}

	public void OnBtnUserInfo()
	{
	}
}
