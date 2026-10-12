using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using Caocao;
using UnityEngine;

public class DuelManager : SceneSingleton<DuelManager>
{
	public DuelCoordinatesManager m_CoordManager;

	private DuelCmdManager m_CmdManager;

	private GameObject m_duelHierarchy;

	private DuelMapController m_duelMapController;

	private DuelCameraController m_duelCameraController;

	private DuelDialogBox m_duelDialogBox;

	private GameObject m_unitOwn;

	private GameObject m_unitEnemy;

	private GameObject m_battleMainCamera;

	private GameObject m_battleGUICamera;

	private GameObject m_battleMinimapCamera;

	private bool m_cmdFinished;

	private bool m_duelStarted;

	private int enemyInitPosX;

	private int ownInitPosX;

	private string m_dialogWindowName;

	private int m_duelMapId;

	public GameObject duelHierarchy
	{
		get
		{
			return null;
		}
	}

	public DuelDialogBox duelDialogBox
	{
		get
		{
			return null;
		}
	}

	public GameObject ownUnit
	{
		get
		{
			return null;
		}
	}

	public int ownUnitId
	{
		get
		{
			return 0;
		}
	}

	public UserUnit ownUserUnit { get; private set; }

	public GameObject enemyUnit
	{
		get
		{
			return null;
		}
	}

	public int enemyUnitId
	{
		get
		{
			return 0;
		}
	}

	public UserUnit enemyUserUnit { get; private set; }

	public GameObject battleMainCamera
	{
		get
		{
			return null;
		}
	}

	public bool cmdFinished
	{
		get
		{
			return false;
		}
	}

	public bool duelStarted
	{
		get
		{
			return false;
		}
	}

	public string lastPlayedBattleBGMName { get; set; }

	private int unitInitPosY
	{
		get
		{
			return 0;
		}
	}

	public string dialogWindowName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int duelMapId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	private string duelBackground
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void AttachCmdManager()
	{
	}

	private void FindDuelMapController()
	{
	}

	private void FindDuelCameraController()
	{
	}

	private void InitManager()
	{
	}

	[DebuggerHidden]
	public IEnumerator StartDuel()
	{
		return null;
	}

	public void StartProcessCommand()
	{
	}

	public void ProcessStarted()
	{
	}

	public void ProcessFinished()
	{
	}

	[DebuggerHidden]
	public IEnumerator StartDuelFromDuelScene()
	{
		return null;
	}

	public void CallStartDuel()
	{
	}

	public void CallStartDuelFromDuelScene()
	{
	}

	public void StopDuel()
	{
	}

	public void SetUnit(int unitId1, int unitId2)
	{
	}

	public void SetUnit(UnitInfo ownUnitInfo, UnitInfo enemyUnitInfo)
	{
	}

	public void SetUnit(TargetInfo ownTargetUnitInfo, TargetInfo enemyTargetUnitInfo)
	{
	}

	public void SetUnit(GameObject ownBattleUnit, GameObject otherBattleUnit)
	{
	}

	public void SetUnit(int ownUnitID, int ownLookUnitId, int ownSkinId, int ownJobType, int ownJobLevel, int enemyUnitID, int enemyJobType, int enemyJobLevel)
	{
	}

	private void OnDrawGizmos()
	{
	}

	public Vector3 GetWorldPosByFloatGridPos(Vector2 floatGridPos)
	{
		return default(Vector3);
	}

	public Vector3 GetWorldPosByGridPos(IntVector2 gridPos)
	{
		return default(Vector3);
	}

	public Vector3 GetWorldPosByGridPos(int x)
	{
		return default(Vector3);
	}

	public IntVector2 GetGridPosByWorldPos(Vector3 worldPos)
	{
		return default(IntVector2);
	}

	public void SetDuelUnitData(GameObject targetObj, int unitID, int lookUnitId, int skinId, int jobTypeId, int jobLevel, UnitSide unitSide, UnitDirection direction)
	{
	}

	public void ResetUnitPosForTest()
	{
	}

	public void CreateUnitforTest()
	{
	}

	public void MakeNewCmd(EnumData.DuelCommands cmdType, params object[] args)
	{
	}

	public void ProcessCommand()
	{
	}

	public List<string> GetCommandNamesInQueue()
	{
		return null;
	}

	public void ClearCommands()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void DoProcessCommandOnce()
	{
	}

	public void ToggleStepwiseMode()
	{
	}

	public bool IsStepwiseMode()
	{
		return false;
	}
}
