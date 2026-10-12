using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using Caocao;
using UnityEngine;
using UnityEngine.SceneManagement;

public class EventScenario : SceneSingleton<EventScenario>
{
	public class VariableTrigger
	{
		private readonly Dictionary<int, int> m_variables;

		public List<int> triggeredTurns;

		private VariableTrigger(VariableTrigger other)
		{
		}

		public VariableTrigger(Dictionary<int, int> variables)
		{
		}

		public VariableTrigger Clone()
		{
			return null;
		}

		public bool Check(int changedVariableIndex, List<int> localVariables)
		{
			return false;
		}
	}

	public bool isTutorial;

	public bool enableSceneSave;

	public List<EventScene> scenes;

	public List<EventScene> tutorialScenes;

	public List<EventScene> helpScenes;

	private Coroutine m_mainCoroutine;

	private Coroutine m_tutorialCoroutine;

	private Coroutine m_helpCoroutine;

	private bool m_isMenuOn;

	public bool isAutoProcess;

	private bool m_isFastForwardProcess;

	private Action<bool> OnSkip__BackingField;

	public bool m_sceneSkip;

	private int m_sceneSkipIdx;

	public string tutorialScriptName;

	public string eventFileName;

	public string helpScriptName;

	public string nextSceneName;

	public bool nextSceneAutoDestroy;

	public string uiCompStatus;

	public bool isBattleStart;

	private Queue<string> m_tutorialQueue;

	public EventCmdBase.MapVariable mapVariable;

	public List<EventCmdBase.ImportantArea> importantArea;

	public bool remindImportantArea;

	public string winCondition;

	public string loseCondition;

	public GameObject objectHolder;

	private Caocao.Weather m_nextWeather;

	public int m_prevTimeScaleStep;

	private Dictionary<int, int> m_changeLookUnitIDs;

	private List<EventCmdBase.RewardItemInfo> m_gainedRewardsInThisBattle;

	public bool isFirst0x1CProcessd;

	public Action OnEventLoopStarted;

	public Action OnEventLoopEnded;

	public Action OnEventAutoSettingChanged;

	private Dictionary<GameObject, EventCmdBase> obj2OnGoingCmd;

	private static readonly int maxLocalVarCount;

	private List<int> _localVariables_;

	public int lastSelectionVar;

	public GameObject lastSelectedUnitObj;

	public UnitData lastActUnitData;

	public bool battleReady;

	private List<GameObject> m_objsEmoticon;

	private Dictionary<int, VariableTrigger> m_triggers;

	public bool isRunning
	{
		get
		{
			return false;
		}
	}

	public bool isRunningMain { get; private set; }

	public bool isTutorialRunning { get; private set; }

	public bool isHelpRunning { get; private set; }

	public bool willStopHelp { get; private set; }

	public bool isRunningOrExistQueue
	{
		get
		{
			return false;
		}
	}

	public bool isMenuOn
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isFastForwardProcess
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isAuto
	{
		get
		{
			return false;
		}
	}

	public Caocao.Weather volatileNextWeather
	{
		get
		{
			return (Caocao.Weather)0;
		}
		set
		{
		}
	}

	public bool mainQuitting { get; private set; }

	public List<int> localVariables
	{
		get
		{
			return null;
		}
	}

	public event Action<bool> OnSkip
	{
		add
		{
		}
		remove
		{
		}
	}

	public void ApplyOrRevertMenuOn(bool value)
	{
	}

	public void AddGainedRewarItemsInThisBattle(int itemId, int amount, int saveFlag, int setId)
	{
	}

	public void DeleteGainedRewardsInThisBattle(int rewardId, int saveFlag)
	{
	}

	public string SerializeGainedRewardsInThisBattle()
	{
		return null;
	}

	private void Awake()
	{
	}

	private void Start()
	{
	}

	protected override void OnDestroy()
	{
	}

	public bool Run(Action callback)
	{
		return false;
	}

	public bool RunTutorial(Action callback)
	{
		return false;
	}

	public void EnqueueTutorial(string tutorialScript)
	{
	}

	private void LoadAndRunTutorial(string tutorialScript)
	{
	}

	public Coroutine RunAsCoroutine()
	{
		return null;
	}

	public bool RunHelp(Action callback)
	{
		return false;
	}

	[DebuggerHidden]
	private IEnumerator _Run(Action callback)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator _RunTutorial(Action callback)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator _RunHelp(Action callback)
	{
		return null;
	}

	public bool willHold()
	{
		return false;
	}

	[DebuggerHidden]
	private IEnumerator _RunTutorialEvent()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator _RunMainEvent(Action callback)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator _RunHelpEvent()
	{
		return null;
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnLevelFinishedLoading(Scene scene, LoadSceneMode mode)
	{
	}

	public void WaitOneFrame(Action callback)
	{
	}

	[DebuggerHidden]
	private IEnumerator _WaitOneFrame(Action callback)
	{
		return null;
	}

	public void WaitForSeconds(float seconds, Action callback)
	{
	}

	public Coroutine WaitForSecondsAsCoroutin(float seconds)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator _WaitForSeconds(float seconds, Action callback)
	{
		return null;
	}

	protected EventScene NextScene(List<EventScene> sceneList)
	{
		return null;
	}

	public static List<EventCmdBase> Find<T>(List<T> cmds, Type type) where T : EventCmdBase
	{
		return null;
	}

	public static List<EventCmdBase> Find<T>(List<T> cmds, Func<EventCmdBase, bool> filter) where T : EventCmdBase
	{
		return null;
	}

	public List<EventCmdBase> Find(Type type)
	{
		return null;
	}

	public List<short> GetAddr(EventCmdBase eventCmd)
	{
		return null;
	}

	public EventCmdBase GetEvent(List<short> addr)
	{
		return null;
	}

	public EventCmdBase GetOngoingCmd(Component unitComponent)
	{
		return null;
	}

	public EventCmdBase GetOngoingCmd(GameObject obj)
	{
		return null;
	}

	public void SetOnGoingCmd(GameObject obj, EventCmdBase onGoing)
	{
	}

	public void RemoveOngoingCmd(GameObject obj, EventCmdBase onGoing)
	{
	}

	public void ResetOnChangeScene()
	{
	}

	public void ResetMain()
	{
	}

	public void ResetTutorial()
	{
	}

	public void ResetHelp()
	{
	}

	public void StopHelp()
	{
	}

	public void ExtractForcedAndBannedUnit()
	{
	}

	public void ExtractForcedAndBannedUnit(List<EventScene> _scenes)
	{
	}

	public List<int> ExtractForcedUnit(List<EventScene> _scenes)
	{
		return null;
	}

	public int ExtractSpawInfo(int routeIndex)
	{
		return 0;
	}

	public List<EventCmdBase> ExtractDefenceSpawInfo(int routeIndex)
	{
		return null;
	}

	public List<EventCmdBase> ExtractDeckSetting(int routeIndex)
	{
		return null;
	}

	public List<EventCmdBase> ExtractCommandAsTacticsType(Type type, int routeIndex)
	{
		return null;
	}

	public void LoadMainEventLocalization()
	{
	}

	private void SaveScenarioSaveState(EventScene curScene)
	{
	}

	public void LoadScenarioSaveState(EventScene currentScene)
	{
	}

	public static int ExtractSpawInfo<T>(List<T> cmds, int routeIndex) where T : EventCmdBase
	{
		return 0;
	}

	public static List<EventCmdBase> ExtractDefenceSpawInfo<T>(List<T> cmds, int routeIndex) where T : EventCmdBase
	{
		return null;
	}

	public static List<EventCmdBase> ExtractControlUnitPassives<T>(List<T> cmds, int routeIndex) where T : EventCmdBase
	{
		return null;
	}

	public static List<EventCmdBase> ExtractThousandDeckSetting<T>(List<T> cmds, int routeIndex) where T : EventCmdBase
	{
		return null;
	}

	public static List<EventCmdBase> ExtractDeckSetting<T>(List<T> cmds, int routeIndex) where T : EventCmdBase
	{
		return null;
	}

	public static List<EventCmdBase> ExtractDefenceDeckSetting<T>(List<T> cmds, int routeIndex) where T : EventCmdBase
	{
		return null;
	}

	public static List<EventCmdBase> ExtractCastleInfo<T>(List<T> cmds, int routeIndex) where T : EventCmdBase
	{
		return null;
	}

	public static List<EventCmdBase> ExtractDualSetting<T>(List<T> cmds) where T : EventCmdBase
	{
		return null;
	}

	public void SetTourneyInfo()
	{
	}

	public static List<EventCmdBase> ExtractMissionInfo<T>(List<T> scenes) where T : EventCmdBase
	{
		return null;
	}

	public static void ExtractBattleInfo<T>(List<T> scenes) where T : EventCmdBase
	{
	}

	public static List<EventCmdBase> ExtractUnitEquipItems<T>(List<T> cmds) where T : EventCmdBase
	{
		return null;
	}

	public void ExtractLocalSaveFlag()
	{
	}

	public void ExtractLocalSaveFlag(List<EventScene> scenes)
	{
	}

	private bool InitVariablesListIfNecessary()
	{
		return false;
	}

	public void ApplyOrRevertVariable(int index, int value)
	{
	}

	public void SetVariable(int index, int value, int prevValue)
	{
	}

	public int GetVariable(int index)
	{
		return 0;
	}

	public void InitializeLocalVars()
	{
	}

	public bool IsUsingLocalVars(int index)
	{
		return false;
	}

	public void AddEmoticonObj(GameObject obj)
	{
	}

	public void DestroyEmoticons()
	{
	}

	public void SetVariableTrigger(int triggerIndex, List<int> trueVars, List<int> falseVars, bool doTrigger)
	{
	}

	public void _SetVariableTrigger(int triggerIndex, VariableTrigger variableTrigger, bool doTrigger)
	{
	}

	public VariableTrigger GetVariableTriggerDeepCopied(int triggerIndex)
	{
		return null;
	}

	public void ResetVariableTrigger(int triggerIndex)
	{
	}

	public void ResetVariableTriggersAll()
	{
	}

	private void _CheckVariableTriggers(int variableIndex)
	{
	}

	public void OccurTrigger(int triggerIndex)
	{
	}

	public void _OccurTrigger(int triggerIndex)
	{
	}

	public void _CancelTrigger(int triggerIndex)
	{
	}

	public bool IsTriggered(int triggerIndex)
	{
		return false;
	}

	public int GetTriggeredFirstTurn(int triggerIndex)
	{
		return 0;
	}

	public int GetTriggeredCount(int triggerIndex)
	{
		return 0;
	}

	public int GetChangeLookUnitID(int key)
	{
		return 0;
	}

	public void SetChangeLookUnitID(int key, int value)
	{
	}

	public void RevertChangeLookUnitID(int key, int beforeValue)
	{
	}
}
