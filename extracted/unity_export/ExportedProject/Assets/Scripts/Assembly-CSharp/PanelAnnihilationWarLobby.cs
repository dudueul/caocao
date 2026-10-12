using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class PanelAnnihilationWarLobby : PanelSelfRegister2UIComponentsMonobehaviour
{
	public List<UIAnnihilationWarMapInfo> battleMapInfos;

	public UILabelLocalized userName;

	public UILabelLocalized userLevel;

	public UITexture userFace;

	public UIAnnihilationWarUserRank userRankUI;

	public GameObject btnUserInfo;

	public UILabelLocalized seasonStat;

	public UISprite nextTierIconSprite;

	public UILabelLocalized nextTierScore;

	public GameObject nextTierObj;

	public UILabelLocalized remainTime;

	public UILabelLocalized ticketCount;

	public UILabelLocalized ticketCoolTime;

	public UIButton ticketChargeButton;

	public GameObject enterableIcon;

	public GameObject notEnterableIcon;

	public UILabelLocalized enterableLabel;

	public UIRankingRewardListItem userRankRewardUI;

	public GameObject emptyDimObj;

	public UITexture leagueIcon;

	public UILabelLocalized leagueName;

	public UILabelLocalized leagueDetail;

	public const string popupName = "PanelAnnihilationWarLobby";

	private const int RewardIconDepth = 6;

	private const int RemainTimeCallDuration = 60;

	private bool m_showingExitPopup;

	protected override void Awake()
	{
	}

	public static void Show()
	{
	}

	public void GotoMatch()
	{
	}

	[DebuggerHidden]
	private IEnumerator GetMatchInfo()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowWarMatch()
	{
		return null;
	}

	private List<UserUnitGroup.UnitInfo> GetSortedUnitIds()
	{
		return null;
	}

	public void GotoEditDeck()
	{
	}

	public void GotoWorldMap()
	{
	}

	public void ShowRewardList()
	{
	}

	public void ShowRanking()
	{
	}

	public void ShowScoreDetail()
	{
	}

	public void ShowStatisticInfo()
	{
	}

	[DebuggerHidden]
	private IEnumerator OnGetPvpUnitStatistics()
	{
		return null;
	}

	public override void ShowPanel()
	{
	}

	private void ShowAlertPanel(string title, string text)
	{
	}

	[DebuggerHidden]
	private IEnumerator GetAnnihilationWarInfos()
	{
		return null;
	}

	public override void ClosePanel()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUserInfo()
	{
	}

	private void UpdateRemainingTime()
	{
	}

	private void UpdateTicket()
	{
	}

	private void ChargeTicket()
	{
	}

	private void UpdateReward()
	{
	}

	private void UpdatePreviousSeasonReward()
	{
	}

	public void ShowBattleInfo()
	{
	}

	public void OnClickProfile()
	{
	}

	public void OnClickLordInfo()
	{
	}

	protected override void OnDestroy()
	{
	}
}
