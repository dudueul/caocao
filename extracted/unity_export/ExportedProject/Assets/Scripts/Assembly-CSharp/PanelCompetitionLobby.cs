using System;
using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class PanelCompetitionLobby : PanelSelfRegister2UIComponentsMonobehaviour
{
	[SerializeField]
	private UIMultiplayMapInfo mapInfo;

	[SerializeField]
	private GameObject mapStatisticInfo;

	[SerializeField]
	private UILabelLocalized userName;

	[SerializeField]
	private UILabelLocalized userLevel;

	[SerializeField]
	private UITexture userFace;

	[SerializeField]
	private UIMultiplaySeasonStat seasonStatUI;

	[SerializeField]
	private UIBattleFormationFaces deckUI;

	[SerializeField]
	private UIBattleFormationFaces brawlDeckUI;

	[SerializeField]
	private GameObject btnUserInfo;

	[SerializeField]
	private UITexture seasonIcon;

	[SerializeField]
	private UILabelLocalized seasonName;

	public UILabelLocalized remainTime;

	public GameObject watchObj;

	public LabelWatchMemeberCount userCount;

	public GameObject allowWatchCheck;

	[SerializeField]
	private UITexture boxIcon;

	[SerializeField]
	private UILabelLocalized boxName;

	public GameObject replayButton;

	public UILabelLocalized dodgePaneltyTimeLabel;

	[SerializeField]
	private GameObject normalRemainedTimeObj;

	[SerializeField]
	private GameObject battlePossibleTimeObj;

	[SerializeField]
	private GameObject hotTimeObj;

	[SerializeField]
	private UILabelLocalized hotTimeNoticeObj;

	[SerializeField]
	private UILabelLocalized hotTimeTitleLabel;

	[SerializeField]
	private UILabelLocalized hotTimeRemainedTimeLabel;

	[SerializeField]
	private PopupHotTimeReward hotTimerewardPopup;

	[SerializeField]
	private GameObject fairRuleBtnObj;

	private CustomEventHotTime m_currentHotTime;

	public const string popupName = "PanelCompetitionLobby";

	private const int RemainTimeCallDuration = 60;

	private bool m_showingExitPopup;

	protected override void Awake()
	{
	}

	public static void Show()
	{
	}

	public static void CallUpdateDodgePaneltyTime()
	{
	}

	public static void CallUpdateAllowWatch()
	{
	}

	public static void CallMatchCancled()
	{
	}

	public static void CallFriendlyMatchCancled()
	{
	}

	public static void CallLeaveFriendlyMatching()
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	public void StartMatchmaking()
	{
	}

	public void ShowFriendlyMatchmakingPopup()
	{
	}

	public void ShowReplayListPopup()
	{
	}

	private void ShowSeasonOffPopup()
	{
	}

	[DebuggerHidden]
	private IEnumerator GetCompetitionInfos()
	{
		return null;
	}

	private void OnMatchCanceled()
	{
	}

	private void OnLeaveFriendlyMatching()
	{
	}

	private void OnFriendlyMatchCanceled()
	{
	}

	private void OnInvitedFriendlyMatch(string nationName, string roomId)
	{
	}

	private void OnJoinedFriendlyMatchingRoom(FriendlyMatchingRoom roomInfo)
	{
	}

	private void OnErrorOnFriendlyMatching(EnumData.FriendlyMatchingError error, JsonData jsonData)
	{
	}

	private void OnUserRegisteredCallback()
	{
	}

	[DebuggerHidden]
	private IEnumerator GetMatchInfo(bool isFriendly, Action callback)
	{
		return null;
	}

	private bool CheckDeckCost(UserUnitGroup userUnitGroup)
	{
		return false;
	}

	public void GotoEditFormation()
	{
	}

	public void GotoEditDeck()
	{
	}

	public void GotoWorldMap()
	{
	}

	private void RemoveMultiplayCallback()
	{
	}

	public void ShowRewardList()
	{
	}

	public void ShowRanking()
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

	public void ShowScoreDetail()
	{
	}

	public void ShowBattleInfo()
	{
	}

	private void ShowAlertPanel(string title, string text)
	{
	}

	public void ToggleSetWatch()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUserInfo()
	{
	}

	private void UpdateSeasonInfo()
	{
	}

	private void UpdateRemainingTime()
	{
	}

	private void UpdateReward()
	{
	}

	private void UpdatePreviousSeasonReward()
	{
	}

	private void UpdateDeckUI()
	{
	}

	private void UpdateWatchInfo()
	{
	}

	private void UpdateHotTimeEventInfo()
	{
	}

	private void UpdateFairRule()
	{
	}

	private void ShowDeckUI()
	{
	}

	private void UpdateButtons()
	{
	}

	private void UpdateDodgePaneltyTime()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void OnClickProfile()
	{
	}

	public void OnClickLordInfo()
	{
	}

	public void OnClickHotTimeInfo()
	{
	}

	public void OnWatchMultiplay()
	{
	}
}
