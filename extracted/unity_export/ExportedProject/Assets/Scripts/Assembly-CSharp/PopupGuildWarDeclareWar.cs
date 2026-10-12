using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using GuildWar;
using LitJson;
using UnityEngine;

public class PopupGuildWarDeclareWar : CaocaoUIModalPopup
{
	private enum ScheduleStatus
	{
		None = 0,
		Attend = 1,
		Battle = 2
	}

	private enum TownInfoBtnState
	{
		None = 0,
		Declare = 1,
		Join = 2,
		GiveUp = 3
	}

	private enum TownState
	{
		None = 0,
		Attended = 1,
		Attendable = 2,
		Attacking = 3,
		Defencing = 4
	}

	private enum RewardPriority
	{
		Gold = 0,
		TreasureEnhanceKeeper = 1,
		TreasureBoxTicket = 2,
		Diploma = 3,
		ExchangeTreasure = 4,
		Silver = 5,
		Food = 6,
		Etc = 7
	}

	[SerializeField]
	private Transform _townList;

	[SerializeField]
	private UIButton _btnLeague;

	[SerializeField]
	private GameObject _popupLeague;

	[SerializeField]
	private UIGrid _popupLeagueGrid;

	[SerializeField]
	private UISprite _popupLeagueGridBG;

	[SerializeField]
	private UILabelLocalized _guildWarPeriod;

	[SerializeField]
	private Transform _flow;

	[SerializeField]
	private UILabelLocalized _reward;

	[SerializeField]
	private UIButton _btnClose;

	[SerializeField]
	private GameObject _townInfo;

	[SerializeField]
	private GameObject _townInfoBg;

	[SerializeField]
	private Transform _townProfile;

	[SerializeField]
	private UISprite _townImage;

	[SerializeField]
	private UILabelLocalized _townScaleLabel;

	[SerializeField]
	private Transform _ownerGuild;

	[SerializeField]
	private Transform _ownerGuildDim;

	[SerializeField]
	private UIScrollView _townRewardScrollView;

	[SerializeField]
	private UIGrid _townRewardGrid;

	[SerializeField]
	private UIGrid _attackerGrid;

	[SerializeField]
	private GameObject _townAttackerInfo;

	[SerializeField]
	private UIGrid _attackerInfoGrid;

	[SerializeField]
	private GameObject _townBattleInfo;

	[SerializeField]
	private UIGrid _townBattleInfoGrid;

	[SerializeField]
	private GameObject _btnDeclare;

	[SerializeField]
	private GameObject _btnGiveUp;

	[SerializeField]
	private GameObject _btnJoin;

	[SerializeField]
	private GameObject _btnResult;

	[SerializeField]
	private UnitDataForRenderer resultBtnUnitRenderer;

	[SerializeField]
	private UIToggle _showGuildMarkToggle;

	private static PopupGuildWarDeclareWar _self;

	private Dictionary<string, Transform> _townListChilds;

	private List<GameObject> _flowChilds;

	private List<Transform> _attackerGridChilds;

	private List<Transform> _attackerInfoGridChilds;

	private List<Transform> _townBattleInfoGridChilds;

	private List<UIButton> _popupLeagueGridChilds;

	private ulong _myGuildId;

	private int _seasonKey;

	private int _seasonDetailGroupIndex;

	private int _seasonDetailKey;

	private int _latestSeasonDetailGroupIndex;

	private int _latestSeasonDetailKey;

	private List<int> _townIds;

	private int _battleWorldId;

	private int _townId;

	private bool isWaitGuildWarRequest;

	private string responseHashValue;

	private bool goingBattleScene;

	private ScheduleStatus _scheduleStatus;

	private GuildWarScheduleDetailDateType _dateType;

	private bool _isBtnDeclareGuildGrade;

	private bool _isBtnDeclareCheckOwner;

	private bool _isBtnDeclareCanApply;

	private bool _isInit;

	private bool m_leftUIShowGuildMark;

	private float _popupLeagueGridCellHeight;

	private DateTime m_prevRequestTown;

	private bool m_invalidLeagueButtonUI;

	private HashSet<int> m_invalidTownIds;

	private bool m_invalidSelectedTownInfoType;

	private bool m_invalidSelectedTownButtonUI;

	private bool m_invalidSelectedTownBattleUI;

	private int MaximumAttendCount;

	private bool m_invalidSelectedTownAttendUI;

	private bool m_invalidSelectedTownUI;

	private HashSet<int> m_townIds;

	private bool m_requestRoughBattleWorld;

	private bool m_requestingRoughBattleWorld;

	private float m_latestResponseRoughBattleWorld;

	private bool m_firstEnding;

	private void SetLeftUIShowGuildMark(bool value)
	{
	}

	private void LoadLeftUIShowGuildMark()
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

	protected override void OnDestroy()
	{
	}

	public static void Show()
	{
	}

	public void OnClickChangeLeftUIShowType()
	{
	}

	public void OnClickColorFocusShow(ulong targetGuildId)
	{
	}

	public void OnClickColorFocusShowNoGuildId()
	{
	}

	private void OnClickShowLeagueGrid()
	{
	}

	private void ChangeTownInfoBtnState(TownInfoBtnState btnState)
	{
	}

	public void OnClickBtnClose()
	{
	}

	public void OnClickSelectTown(int townId)
	{
	}

	public void OnClickChangeBattleWorld(int battleWorldId)
	{
	}

	public void OnClickGoBattle()
	{
	}

	public void OnClickDiscardTownStart()
	{
	}

	public void OnClickMapPreview()
	{
	}

	public void OnClickBtnResult()
	{
	}

	public void OnClickGuildWarAttend()
	{
	}

	private void OnRequestSiegeAttend(JsonData response)
	{
	}

	[DebuggerHidden]
	private IEnumerator RequestSiegeAttend(float waitTime)
	{
		return null;
	}

	private void goBattleScene(bool isSuccess)
	{
	}

	private void UpdateCurrentSchedule()
	{
	}

	private void Update()
	{
	}

	private void OnScheduleDeserialized(HashSet<GuildWarSchedule> datas)
	{
	}

	private void OnScheduleDetailGroupDeserialized(HashSet<GuildWarScheduleDetailGroup> datas)
	{
	}

	private void OnScheduleDetailDeserialized(HashSet<GuildWarScheduleDetail> datas)
	{
	}

	private void OnAttendDeserialized(HashSet<GuildWarAttend> datas)
	{
	}

	private void OnBattleJoinDeserialized(HashSet<GuildWarBattleJoin> datas)
	{
	}

	private void OnGuildDeserialized(HashSet<GuildInfo> datas)
	{
	}

	private void OnGuildActivityPointDeserialized(HashSet<GuildWarRankPoint> datas)
	{
	}

	private void OnTownDeserialized(HashSet<GuildWarWorldTown> datas)
	{
	}

	private void InvalidateUIsAll()
	{
	}

	private void UpdateUIsAll()
	{
	}

	private string GetGuildWarWorldPeriod(GuildWarScheduleDetail scheduleDetail)
	{
		return null;
	}

	private void InvalidateLeagueButtonUI()
	{
	}

	private void UpdateLeagueButtonUI()
	{
	}

	private void UpdateSeasonDetailUI(DateTime clientServerTime)
	{
	}

	private string GetRewardGrade(int grade)
	{
		return null;
	}

	private void InvalidateTownUI(int p_townId)
	{
	}

	private void UpdateTownUI(int p_townId)
	{
	}

	private void InvalidateTownUIs()
	{
	}

	private void UpdateTownUIs()
	{
	}

	private void UpdateScheduleStatus()
	{
	}

	private void InvalidateSelectedTownInfoType()
	{
	}

	private void UpdateSelectedTownInfoType()
	{
	}

	private void InvalidateSelectedTownButtonUI()
	{
	}

	private void UpdateSelectedTownButtonUI()
	{
	}

	private void InvalidateSelectedTownBattleUI()
	{
	}

	private void UpdateSelectedTownBattleUI()
	{
	}

	private void InvalidateSelectedTownAttendUI()
	{
	}

	private void UpdateSelectedTownAttendUI()
	{
	}

	private void banDeclareWarCommentText(string Name, string Value)
	{
	}

	private void DeclareWarCommentText(string Name, string Value)
	{
	}

	private void minimumRequierText(int minimumRequireExp)
	{
	}

	private bool isOwnerNPC(GuildWarWorldTown town)
	{
		return false;
	}

	private RewardPriority GetRewardPriority(RewardInfo rewardInfo)
	{
		return RewardPriority.Gold;
	}

	private void InvalidateSelectedTownUI()
	{
	}

	private void UpdateSelectedTownUI()
	{
	}

	private void SetGuildName(Transform trGuild, string guildName, int emblemIndex, int guildwarRank)
	{
	}

	private void SetGuild(Transform trGuild, Transform trDim, bool isActive, ulong guildId, string guildName, int emblemIndex, int guildwarRank)
	{
	}

	private void dataClear()
	{
	}

	private void initData()
	{
	}

	private void termData()
	{
	}

	private void ShowBarracks()
	{
	}

	private void onRequestDiscardTown(string hashValue)
	{
	}

	private void OnGuildWarDiscardTownStart(JsonData response)
	{
	}

	private void OnGuildWarDiscardTown(JsonData response)
	{
	}

	private void OnGuildWarMyData(JsonData response)
	{
	}

	private void StartRequestRoughBattleWorld()
	{
	}

	private void StopRequestRoughBattleWorld()
	{
	}

	private void UpdateRequestRoughBattleWorld()
	{
	}

	private void OnGuildWarRoughBattleWorld(JsonData response)
	{
	}

	[DebuggerHidden]
	private IEnumerator CheckFirstOrResult()
	{
		return null;
	}
}
