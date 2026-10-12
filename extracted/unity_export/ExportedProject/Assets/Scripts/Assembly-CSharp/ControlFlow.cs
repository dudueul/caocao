using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using Caocao;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

public class ControlFlow : SceneSingleton<ControlFlow>
{
	public enum statusType
	{
		IDLE = 0,
		TURN_START = 1,
		SELECT_UNIT = 2,
		MOVING = 3,
		MOVED = 4,
		ATTACK = 5,
		ARTIFICE = 6,
		USEITEM = 7,
		DOSOMETHING = 8,
		PINCER = 9,
		END_OWN_TURN = 10,
		MULTI_TARGET_ATK = 11,
		MULTI_TARGET_ART = 12,
		DONE = 13,
		CHECK_RESULT = 14,
		TURN_TRANSITION = 15,
		QUICK_ARTIFICE = 16,
		QUICK_USEITEM = 17,
		USER_SPELL = 18,
		ARENA_SETTING = 19
	}

	public enum TurnType
	{
		BySide = 0,
		OneByOne = 1,
		Num = 2
	}

	public enum UnitSpecificCount
	{
		Pincer = 0,
		SwapSlot = 1,
		StartPosX = 2,
		StartPosY = 3,
		StartDir = 4,
		RevivalCnt = 5,
		Num = 6
	}

	public delegate void TurnCycleEventHandler();

	public UnitSide[] m_currentTurnOrder;

	public UnitSide[] m_ownerTurnOrder;

	public UnitSide[] m_enemyTurnOrder;

	public Action OnControllingUnitChanged;

	private UnitData m_controllingUnit;

	public UnitData targetUnitData;

	private statusType m_controlBeforeStatus;

	private statusType m_controlStatus;

	private ObscuredInt m_controlSkillId;

	private ObscuredInt m_controlItemId;

	private ObscuredInt m_lastControlItemId;

	public Action OnControlSKillIdChanged;

	public Action OnControlItemIdChanged;

	private Action<statusType> OnStatusChanged__BackingField;

	private List<UnitData> m_aiUnits;

	private bool m_entrustTurn;

	private bool m_entrustBattle;

	private static string FAST_FORWARD_USED;

	private ObscuredBool m_fastforward;

	private Action<bool> OnFastForwardChange__BackingField;

	public ObscuredBool fastforwardPaid;

	public ObscuredInt usedUndoActionCount;

	public ObscuredInt usedUndoTurnCount;

	public ObscuredInt usedUndoActionCountByTurn;

	public bool requestingFastforward;

	public EnumData.AttackType atkType;

	private ObscuredInt m_turnExtended;

	public EnumData.AttackType flowAtkType;

	private List<UnitData> m_aiUnitDatas;

	private ObscuredBool m_RevertTurn;

	public ObscuredBool isBattleStart;

	public ObscuredBool isEndBattle;

	private ObscuredInt m_prevTurn;

	private ObscuredInt m_currentTurn;

	private int m_currentUnitTurnIdx;

	private ObscuredEnum<UnitSide> m_currentTurnOwner;

	public TurnType turnType;

	public Action OnUnitTurnCompleted;

	public static TurnType PvpTurnType;

	private List<UnitData> m_unitTurnOrder;

	private List<UnitData> m_ownUnitOrder;

	private List<UnitData> m_opponentUnitOrder;

	private ByAgi m_oneByOneSorting;

	private int m_currentUnitIdx;

	private int m_unitTurnOrderVersion;

	private int m_unitTurnOrderCachedVersion;

	public Action OnUpdatedUnitTurnOrder;

	private bool changedUnitTurn;

	private Dictionary<UnitData, int>[] m_unitSpecificCount;

	private List<UnitData> listSwapUnit;

	private ObscuredInt m_turnLimitation;

	public ObscuredInt ownerActionCount;

	private Action OnActionReverted__BackingField;

	public Action OnCFCompleted;

	public Action OnCFStarted;

	public Action OnBattleStart;

	public Action OnBattleEnd;

	private Action OnUnitActionCompleted__BackingField;

	private TurnCycleEventHandler OnTurnCycleCompleted__BackingField;

	private Action OnTurnOwnerChanged__BackingField;

	private Action OnTurnReverted__BackingField;

	private GameObject m_currentUnitCursor;

	private UnitData m_controllingUnitBeforeRunningEvent;

	private statusType m_controlStatusBeforeRunningEvent;

	private TopMenuTurnCount m_turnCountUi;

	private UnitSide pendingTurnOwner;

	private bool skippedLoadedArenaFirstTurn;

	public float battlePlayTime;

	public BattleTimer battleTimer;

	public bool battleTimeOver;

	private bool alarmTimeOn;

	private Dictionary<int, DateTime> m_expireTimeDic;

	private readonly string[] closingPanelOnTimeOver;

	private ObscuredBool m_unitExpBoost;

	private Action OnUnitTagCompleted__BackingField;

	public UnitData controllingUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool isControllingUnitInProgress
	{
		get
		{
			return false;
		}
	}

	public statusType controlBeforeStatus
	{
		get
		{
			return statusType.IDLE;
		}
	}

	public statusType controlStatus
	{
		get
		{
			return statusType.IDLE;
		}
		set
		{
		}
	}

	public int controlSkillId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int controlItemId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool isSkillByItem
	{
		get
		{
			return false;
		}
	}

	public int lastControlItemId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool AIModeSilverPaid { get; set; }

	public bool entrustTurn
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private bool entrustBattle
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isEntrust
	{
		get
		{
			return false;
		}
	}

	public bool isUserMode
	{
		get
		{
			return false;
		}
	}

	public bool fastforward
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public ObscuredBool fastforwardPaidByGold { get; private set; }

	public int lastUndoActionCount
	{
		get
		{
			return 0;
		}
	}

	public ObscuredInt turnExtended
	{
		get
		{
			return default(ObscuredInt);
		}
		set
		{
		}
	}

	public bool skipAnimate
	{
		get
		{
			return false;
		}
	}

	public int prevTurn
	{
		get
		{
			return 0;
		}
	}

	public int currentTurn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int currentUnitTurn
	{
		get
		{
			return 0;
		}
	}

	public int currentUnitTurnIdx
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public UnitSide currentTurnOwner
	{
		get
		{
			return UnitSide.OWN;
		}
		set
		{
		}
	}

	public bool isAllControlMode { get; set; }

	public bool isUnitTurn
	{
		get
		{
			return false;
		}
	}

	public bool isBySideTurn
	{
		get
		{
			return false;
		}
	}

	public bool isOneByOne
	{
		get
		{
			return false;
		}
	}

	public bool isOrderedUnitTurn
	{
		get
		{
			return false;
		}
	}

	public List<UnitData> ownUnitOrder
	{
		get
		{
			return null;
		}
	}

	public List<UnitData> opponentUnitOrder
	{
		get
		{
			return null;
		}
	}

	private ByAgi oneByOneSorting
	{
		get
		{
			return null;
		}
	}

	public List<UnitData> unitTurnOrder
	{
		get
		{
			return null;
		}
	}

	public UnitData unitTurnOwner
	{
		get
		{
			return null;
		}
	}

	public int currentUnitIdx
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int turnLimitation
	{
		get
		{
			return 0;
		}
	}

	public int originalTurnLimitation
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int remainedTurn
	{
		get
		{
			return 0;
		}
	}

	private bool isTurnStarted
	{
		get
		{
			return false;
		}
	}

	public bool unitTurnNotificationing { get; private set; }

	private TopMenuTurnCount turnCountUi
	{
		get
		{
			return null;
		}
	}

	public int remainedSeconds
	{
		get
		{
			return 0;
		}
	}

	public DateTime expireTime
	{
		get
		{
			return default(DateTime);
		}
	}

	public bool unitExpBoost
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public event Action<statusType> OnStatusChanged
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<bool> OnFastForwardChange
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action OnActionReverted
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action OnUnitActionCompleted
	{
		add
		{
		}
		remove
		{
		}
	}

	public event TurnCycleEventHandler OnTurnCycleCompleted
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action OnTurnOwnerChanged
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action OnTurnReverted
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action OnUnitTagCompleted
	{
		add
		{
		}
		remove
		{
		}
	}

	protected ControlFlow()
	{
	}

	public void GetEntrustState(out bool entrustTurn, out bool entrustBattle)
	{
		entrustTurn = default(bool);
		entrustBattle = default(bool);
	}

	public bool IsFastForwardClicked()
	{
		return false;
	}

	public void ClearPrevTurn()
	{
	}

	public static void LoadLastTurnType()
	{
	}

	public static void SaveTurnType()
	{
	}

	public static void SaveTurnTypeTo(TurnType type)
	{
	}

	private void InitUnitTurn()
	{
	}

	private List<UnitData> SortUnitOrder(List<UnitData> unitDatas, int[] unitIds)
	{
		return null;
	}

	public void BuildUnitTurnOrderOneByOne(List<UnitData> unitDatas1, List<UnitData> unitDatas2, ref List<UnitData> ordered)
	{
	}

	private void SortUnitTurnOrderOneByOne(ref List<UnitData> unitDatas, bool sortAgi)
	{
	}

	public void SortUnitByAgi(List<UnitData> unitDatas)
	{
	}

	private bool IsOneByOnePair(UnitData unit1, UnitData unit2)
	{
		return false;
	}

	public void InvalidateUnitTurnOrder()
	{
	}

	public void UpdateUnitTurnOrder(bool sortAgi = true)
	{
	}

	public void OnUnitTurnComplete(UnitData completeUnit)
	{
	}

	public List<UnitData> GetAlternativeUnitOrder()
	{
		return null;
	}

	public void SetTurnOwnerByEffectTurn(UnitSide turnOwner)
	{
	}

	public void SetControlStatusSkipCamera(statusType status)
	{
	}

	public void InitActionCountUi()
	{
	}

	public void UpdateActionCountUi()
	{
	}

	private void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void Clear()
	{
	}

	public void BackupStatus()
	{
	}

	public void RecoverStatus()
	{
	}

	public void BattleStart()
	{
	}

	public void BattleEnd()
	{
	}

	public void StartFlow()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitForMultiplayContinue()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator WaitForMultiplayReplay()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator WaitForMultiplayWatch()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator WaitForMultiplayStart()
	{
		return null;
	}

	public void InitializeTurnLimitation()
	{
	}

	public void InitializeTurn()
	{
	}

	private void SetRandomTurnOrder()
	{
	}

	public void OnRevertTurn()
	{
	}

	public void OnRevertAction(int numUndoActions, bool emergency)
	{
	}

	private void StartFirstTurn()
	{
	}

	public void UnitActionCompleted(UnitData unit)
	{
	}

	public void UnitActionCompleted(List<UnitData> units, EnumData.UnitActionChangeReason reason)
	{
	}

	[DebuggerHidden]
	public IEnumerator UnitActionCompletedCoroutine(List<UnitData> units, EnumData.UnitActionChangeReason reason)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ActionCompleteImpl(UnitData unit)
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator ProcessComaUnits()
	{
		return null;
	}

	public void EndTurnSide()
	{
	}

	private bool NeedToPassTurnTo(UnitSide targetTurnOwner)
	{
		return false;
	}

	private void PassTurnSideTo()
	{
	}

	public void SetPendingTurnSide(UnitSide turnSide)
	{
	}

	private void AvailableTurnSide()
	{
	}

	public void SetOwnerTurnOrder()
	{
	}

	public void SetEnemyTurnOrder()
	{
	}

	public bool IsPriorTurn(UnitSide side)
	{
		return false;
	}

	public UnitSide GetPriorTurnSide()
	{
		return UnitSide.OWN;
	}

	private void NextTurnSide(UnitSide current, bool frontCheck)
	{
	}

	private UnitSide GetAvailableTurnSide(UnitSide current, bool frontCheck)
	{
		return UnitSide.OWN;
	}

	private void EndTurn()
	{
	}

	private void NextTurn()
	{
	}

	private void ProcessTurnStart()
	{
	}

	[DebuggerHidden]
	private IEnumerator ProcessTurnStartCoroutine()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator TurnSideAnimation()
	{
		return null;
	}

	private void ContinueBattleSkipTurnIndex()
	{
	}

	[DebuggerHidden]
	private IEnumerator UnitTurnNotification(List<UnitData> units)
	{
		return null;
	}

	public bool OnCancelAction()
	{
		return false;
	}

	public bool OnCancelAction(IntVector2 mouseGridPos)
	{
		return false;
	}

	public void Focus(GameObject target)
	{
	}

	public void TurnOffFocus()
	{
	}

	public void MoveCamera(Component focusingUnit)
	{
	}

	public void MoveCamera(GameObject focusingUnit)
	{
	}

	public void CameraFocusOnLeaderUnit()
	{
	}

	private void UpdateCurrentUnitCursor()
	{
	}

	public void SwitchingCurrentUnitCursor(bool TurnOn)
	{
	}

	private void InitUnitRoleMarker()
	{
	}

	public void StopAllUnitFlickering()
	{
	}

	private void StopUnitFlickering(List<UnitData> units)
	{
	}

	private List<UnitData> GetOrderedAIUnits()
	{
		return null;
	}

	private List<UnitData> OrderUnitsToProcessAI(List<UnitData> units)
	{
		return null;
	}

	public List<UnitData> ReorderAIUnitsForNeighbors(List<UnitData> orderedUnitsList)
	{
		return null;
	}

	public void TurnOnAIAtMyTurn()
	{
	}

	public void TurnOnAI()
	{
	}

	public void TurnOffAI()
	{
	}

	private List<UnitData> CollectAiUnits()
	{
		return null;
	}

	private List<UnitData> OrderUnitDatasToProcessAi()
	{
		return null;
	}

	private List<UnitData> ReorderAiUnitDatasForNeighbors(List<UnitData> orderedUnitsList)
	{
		return null;
	}

	public void StartAI()
	{
	}

	public void OnUnitAIComplete()
	{
	}

	public void ToggleFastForward()
	{
	}

	private void OpenFastForwardSubscriptionPopup()
	{
	}

	private void OpenFastForwardPopup()
	{
	}

	private void Update()
	{
	}

	public void InvalidateAllUnitStats()
	{
	}

	public void SetBattleTimer(bool wait = false)
	{
	}

	public void SetExpireTime(int turnIdx, DateTime time)
	{
	}

	public void AddUnitTurnTime(int addSeconds, int alarmTime)
	{
	}

	private void OnAlarm(int remainSec)
	{
	}

	private void CheckShowAlarm(string text)
	{
	}

	private void OnTimeOver()
	{
	}

	[DebuggerHidden]
	private IEnumerator KeepTrySkipTurn()
	{
		return null;
	}

	private void OnMultiplayUserStateChanged(ulong uid, EnumData.MultiplayUserState state, int memberCnt)
	{
	}

	private void SetBattleTopMiddleUIActive(bool active)
	{
	}

	public bool SkipTurn(bool emitControl = true)
	{
		return false;
	}

	public void CancelAllAction(bool cancelSelectUnit = false)
	{
	}

	public bool SkipControllingUnitAction(bool emitControl = true)
	{
		return false;
	}

	private bool CanSkipTurnStatus()
	{
		return false;
	}

	[DebuggerHidden]
	private IEnumerator SetTimeOver()
	{
		return null;
	}

	private void AddDesyncCheckAlarm()
	{
	}

	public void RemoveDesyncCheckAlarm()
	{
	}

	private void CheckDesync()
	{
	}

	[DebuggerHidden]
	private IEnumerator DoSpActionTurnStart()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator AllOnceConditionPenalty(List<UnitData> units)
	{
		return null;
	}

	public void SpecificCountUnit(UnitSpecificCount countType, UnitData unit, int count = 1)
	{
	}

	public void ClearSpecificCountUnit(UnitSpecificCount countType, UnitData unit)
	{
	}

	public int GetSpecificCountUnit(UnitSpecificCount countType, UnitData unit)
	{
		return 0;
	}

	private void OnChangedUnitStat(UserUnit unit)
	{
	}

	private void ShowPanelMultiplayTurnOrder()
	{
	}

	private List<UnitData> InitOwnUnitTurnOrder()
	{
		return null;
	}

	private List<UnitData> InitOpponentUnitTurnOrder()
	{
		return null;
	}

	public void UnitTurnTag(UnitData fightUnitData, UnitData tagUnitData)
	{
	}

	private bool SetUnitTag()
	{
		return false;
	}

	private bool SetDeadTag()
	{
		return false;
	}

	private void TurnCycleEvent()
	{
	}

	private bool CanStartMultiplayBattle()
	{
		return false;
	}

	public bool IsMultiplayPendingState()
	{
		return false;
	}

	public bool DidAllMoveCompleted()
	{
		return false;
	}

	public void CompleteArenaSetting()
	{
	}

	public bool IsChangedUnitTurn()
	{
		return false;
	}

	public void UpdateLastControlTurn()
	{
	}
}
