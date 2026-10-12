using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class BattleDeck : PanelSelfRegister2UIComponentsMonobehaviour
{
	public enum Type
	{
		BattleDeck = 0,
		UnitManage = 1,
		UnitSelect = 2,
		RepresentDeck = 3
	}

	public delegate void OnSelectUnitDelegate(UserUnit userUnit);

	private Type m_type;

	public UIButton m_btnCancel;

	public UIButton m_btnGoBattle;

	public UIButton m_btnDefence;

	public UIButton m_btnAnnihilationWar;

	public UIButton m_btnRecon;

	public UIButton m_btnEntry;

	public UIButton m_btnAutoAssign;

	public UIButton m_btnAutoAssignCancel;

	public UIButton m_btnSettingQuickDeck;

	public UIButton m_btnSaveQuickDeck;

	public UIButton m_btnConfirm;

	public UIButton m_btnSupportHistory;

	public UIButton m_btnMissionInfo;

	public UIButton m_btnArenaStageInfo;

	public UIButton m_btnArenaSynergyInfo;

	public UILabelLocalized m_panelTitle;

	public UIButton m_btnBack;

	public GameObject costGo;

	public BattleDeckLeftBox battleDeckLeftBox;

	public BattleDeckRightBox battleDeckRightBox;

	public GameObject m_timeGameObject;

	public GameObject thousandBattleGradeInfoGo;

	public List<GameObject> thousandBattleGradeImages;

	public UIThousandBattleRecoveryButton thousandBattleRecoveryButton;

	private UserBattleFormation m_tempUserBattleFormation;

	private UserUnitGroup m_tempUserUnitGroup;

	public GameObject m_btnBattleDeckExMark;

	private int m_needFood;

	public Action OnPickUpFinished;

	private RemainTimerEffect m_RemainTimerEffect;

	private int m_needGold;

	private bool m_started;

	public GameObject m_btnUnitInfo;

	public GameObject m_btnUnitList;

	public GameObject m_btnUnitSelect;

	private OnSelectUnitDelegate OnSelectUnitCallback;

	public Item m_growUpItem;

	private bool m_activeUnitTree;

	private UIThousandBattleRecoveryButton raidBattleRecoveryButton;

	private Coroutine m_timerCoroutine;

	public static float competitionRemainingTime;

	private PanelPopup2Buttons m_closePopup;

	[SerializeField]
	private GameObject m_competitionInfoGo;

	[SerializeField]
	private UILabelLocalized m_competitionMessageLabel;

	[SerializeField]
	private UILabelLocalized m_remainingTimeSecLabel;

	[SerializeField]
	private UILabelLocalized m_remainingTimePointLabel;

	[SerializeField]
	private UILabelLocalized m_remainingTimeFracLabel;

	[SerializeField]
	private GameObject m_btnCompetitionFairRuleBtn;

	[SerializeField]
	private GameObject m_btnSelectBarracksUnits;

	private bool m_isSelectCorpMilitryPhase;

	private bool inited;

	private ulong lastSupportLogSrl;

	private int supportLogPage;

	private bool doNotShowAreaInfo;

	public Type type
	{
		get
		{
			return Type.BattleDeck;
		}
	}

	public UserBattleFormation tempUserBattleFormation
	{
		get
		{
			return null;
		}
	}

	public UserUnitGroup tempUserUnitGroup
	{
		get
		{
			return null;
		}
	}

	private ReconController reconController
	{
		get
		{
			return null;
		}
	}

	public int growUpLevel
	{
		get
		{
			return 0;
		}
	}

	protected override void Awake()
	{
	}

	private void Start()
	{
	}

	public override void ShowPanel()
	{
	}

	public void ShowBattleDeck()
	{
	}

	public void ShowUnitManage()
	{
	}

	public void ShowUnitSelect(Item growUpItem, OnSelectUnitDelegate callback)
	{
	}

	public void ShowRepresentSelect()
	{
	}

	public override void OnInit()
	{
	}

	private void OnEnable()
	{
	}

	private void Init()
	{
	}

	private void InitGuildWar()
	{
	}

	private void InitBattleDeck()
	{
	}

	public void InitOnScenarioMap()
	{
	}

	private void InitOnRelation()
	{
	}

	private void InitOnArena()
	{
	}

	private void InitDefenderOnWorldMap()
	{
	}

	private void OnRecover()
	{
	}

	private void InitThousandBattle()
	{
	}

	private void InitAnnihilationWar()
	{
	}

	private void InitCompetition()
	{
	}

	private void InitCompetitionFriendlyMatch()
	{
	}

	private void InitDefenderOnRecon()
	{
	}

	private void InitOnAttackerRecon()
	{
	}

	private void InitGuildSupport()
	{
	}

	private void InitGuildSiegeWarAttack()
	{
	}

	private void InitGuildSiegeWarSham()
	{
	}

	private void PickGuildSupportDeck()
	{
	}

	private void PickGuildSiegeWarAttackDeck()
	{
	}

	public void UpdateAttackCostUI()
	{
	}

	private void InitOnAttackerBattleFieldRecon()
	{
	}

	public void InitOnRaid(Raid raid)
	{
	}

	public void InitOnWorldBoss(WorldBoss worldBoss)
	{
	}

	private void InitOnTimer()
	{
	}

	private void UpdateRemainTimer()
	{
	}

	public void InitOnInstance(UserInstance userInst)
	{
	}

	public void InitOnInstanceExpedition()
	{
	}

	public void InitOnShamRecon()
	{
	}

	public void OnButtonPress()
	{
	}

	private void OnBtnGoBattleClicked()
	{
	}

	private void OnBtnDefenceClicked()
	{
	}

	private void OnBtnCompetitionClicked()
	{
	}

	private bool CheckCompetitionUnitSelection()
	{
		return false;
	}

	[DebuggerHidden]
	private IEnumerator CloseCompetitionFormation()
	{
		return null;
	}

	private void OnBtnAnnihilationWarClicked()
	{
	}

	private void OnBtnArenaClicked()
	{
	}

	private void OnBtnGuildCompClicked()
	{
	}

	private void OnBtnGuildSupportHistoryClicked()
	{
	}

	private void GoReconMap()
	{
	}

	public void OnClickUnitForm()
	{
	}

	private void LoadReconScene()
	{
	}

	private bool CheckSquadIsFull()
	{
		return false;
	}

	private static bool CheckNeedMoreUnit()
	{
		return false;
	}

	private bool CheckUnitCost()
	{
		return false;
	}

	private bool CheckUnitSelection()
	{
		return false;
	}

	private bool CheckArenaUnitSelection()
	{
		return false;
	}

	private bool CheckRepresentUnitSelection()
	{
		return false;
	}

	private bool CheckCompetitionFormation()
	{
		return false;
	}

	private bool CheckUnitEnergy()
	{
		return false;
	}

	private bool CheckProtectMode()
	{
		return false;
	}

	private bool CheckNoMayolUnit()
	{
		return false;
	}

	public void PayGoBattle()
	{
	}

	public void OnSelectBarracksUnits()
	{
	}

	public void RequestCollectFood()
	{
	}

	public void GoBattle()
	{
	}

	private PanelPopupBase.PopupState DefenceDeckComplete()
	{
		return PanelPopupBase.PopupState.KeepOpen;
	}

	private void AnnihilationWarDeckComplete()
	{
	}

	private void CompetitionDeckComplete()
	{
	}

	private void CompetitionFriendlyDeckComplete()
	{
	}

	private void ArenaDeckComplete()
	{
	}

	private void GuildSupportDeckComplete()
	{
	}

	private void GuildSiegeWarAttackDeckInitComplete()
	{
	}

	[DebuggerHidden]
	private IEnumerator SetAnnihilationWarDeck()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator SetCompetitionDeck()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator SetArenaDeck()
	{
		return null;
	}

	private void OnDefenceDeckComplete(JsonData response)
	{
	}

	public void Close()
	{
	}

	protected override void ClosePanelByShortcut()
	{
	}

	public override void ClosePanel()
	{
	}

	public void UpdateUnitFromPickedUnits()
	{
	}

	public void AutoAssign()
	{
	}

	public void AssignCancel()
	{
	}

	private void CheckQuickDeck(Action callback)
	{
	}

	private void SettingQuickDeck()
	{
	}

	private void SaveQuickDeck()
	{
	}

	private bool MustPossessionUnitCheck()
	{
		return false;
	}

	public static bool TryPick(UserUnit userUnit, bool isQuickBattle = false)
	{
		return false;
	}

	public static bool CheckEmptySlot(UserUnit userUnit)
	{
		return false;
	}

	private static void ShowFailMessage(string msg, params object[] args)
	{
	}

	private static void RequestRemoveDefenseDeck(UserUnit userUnit, string msg, params object[] args)
	{
	}

	public void GetBack()
	{
	}

	private void CheckChangeDefenseDeck(Action callback)
	{
	}

	private bool IsChangeDefenseDeck()
	{
		return false;
	}

	private static int GetAllInjuredUnitsHealCost()
	{
		return 0;
	}

	private void SetUnitFormButton()
	{
	}

	private void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void ProcessAutoInstance()
	{
	}

	public void ReselectUnits()
	{
	}

	private bool CanProcessAutoInstance()
	{
		return false;
	}

	private void StartWaitingForRecoveringEnergy()
	{
	}

	public void ClearAutoProcessTimer()
	{
	}

	private bool CheckFoodForAutoInstance()
	{
		return false;
	}

	private void OnAutoRecoverEnergy(JsonData response)
	{
	}

	private void AutoRecoverEnergy(List<ulong> unitSrls)
	{
	}

	private void GoAutoInstance()
	{
	}

	private bool CheckMaxLevel()
	{
		return false;
	}

	private void InitUnitManage()
	{
	}

	private void CloseUnitManage()
	{
	}

	public void OnClickUnitInfo()
	{
	}

	public void OnClickUnitList()
	{
	}

	private void InitUnitSelect()
	{
	}

	private void CloseUnitSelect()
	{
	}

	public void OnClickUnitSelect()
	{
	}

	private void InitRepresentSelect()
	{
	}

	private void UpdateBottomButtonGrid()
	{
	}

	private void CloseRepresentSelect()
	{
	}

	public void OnSelectedUnitChanged(UserUnit selectedUnit)
	{
	}

	public void SelectMyUnit(int unitId)
	{
	}

	private void ShowMissionInfo()
	{
	}

	private void ShowArenaStageInfo()
	{
	}

	private void ShowArenaSynergyInfo()
	{
	}

	public void InitCompetitionAutoTimer()
	{
	}

	public void InitCompetitionSetFormationUI()
	{
	}

	private void StartTimer(Action autoAction)
	{
	}

	private void StopTimerCoroutine()
	{
	}

	[DebuggerHidden]
	private IEnumerator StartTimerCoroutine(Action autoAction)
	{
		return null;
	}

	private void OnMatchmakingCanceledCallback()
	{
	}

	private void OnUserRegisteredCallback()
	{
	}

	private void RemoveMultiplayCallback()
	{
	}

	public void OnClickCompetitionFairRuleBtn()
	{
	}
}
