using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using CaocaoLobby;
using LitJson;
using UnityEngine;

public class WorldMapController : MonoBehaviour
{
	public enum WorldMapStep
	{
		Lobby = 0,
		GetWorldMapInfo = 1,
		StandBy = 2
	}

	public static Color32 ownTownColor;

	public static Color32 enemyTownColor;

	public static Color32 npcTownColor;

	public GameObject m_Towns;

	public GameObject m_Instances;

	public GameObject m_battleFields;

	public GameObject m_Clouds;

	public GameObject m_OpenRegionBtns;

	public GameObject m_WorldMapEnvironments;

	public OccupiedReteUI m_OccupyInfo;

	public BoxCollider m_UnfocusedCloudColl;

	private GameObject m_FocusedTownObj;

	private GameObject m_openCloudObj;

	[SerializeField]
	private Controller m_lobby;

	public Action OnWorldMapStandby;

	private Dictionary<int, GameObject> m_id2TownObj;

	private int m_focusedCloudId;

	private bool m_waitOfferwall;

	private bool m_waitCustomLoading;

	private int m_lastTownID;

	private int m_lastInstanceID;

	private bool m_didShowConcqueredEffect;

	public bool m_isMovingCameraByClickTown;

	public static WorldMapController instance;

	private static bool ms_autoShowHistory;

	private UICamera m_cameraGo;

	public bool isLobbyOn
	{
		get
		{
			return false;
		}
	}

	public bool isLobbyTransiting
	{
		get
		{
			return false;
		}
	}

	public bool isGuildLobbyOn
	{
		get
		{
			return false;
		}
	}

	public WorldMapStep worldMapStep { get; private set; }

	public bool isOnWorldMapDisplay
	{
		get
		{
			return false;
		}
	}

	private UICamera cameraGo
	{
		get
		{
			return null;
		}
	}

	public bool isTacticMapMode { get; private set; }

	public GameObject GetTownObj(int id)
	{
		return null;
	}

	private void Awake()
	{
	}

	private void Start()
	{
	}

	private void OnUseOffer(JsonData response)
	{
	}

	private void ShowOfferwallResultPopup(string title, string desc)
	{
	}

	private void OnResponseCustomLoading(JsonData response)
	{
	}

	public void OnCompleteLoading()
	{
	}

	[DebuggerHidden]
	private IEnumerator DoPostProcess()
	{
		return null;
	}

	private void OnReceiveMissionReward(int completeNum, GuildMissions.MissionReward reward)
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	public void ToggleAllUIOnToggleUnitTree(bool active)
	{
	}

	public void ToggleAllUI(bool active)
	{
	}

	public void ToggleWorldMapUI(bool isWorldMapStep)
	{
	}

	public void GoMyCapital()
	{
	}

	public void GoTargetTown(int targetTownId, float time)
	{
	}

	public void MoveCameraToFocusedTown(int townId)
	{
	}

	public void MoveCameraToFocusedTown(int townId, float time)
	{
	}

	private void AllocateOpenRegionBtns()
	{
	}

	public void UpdateOpenRegionBtnColor()
	{
	}

	public void UpdateOpenRegionBtn()
	{
	}

	private void SetOpenRegionBtn(int cloudId)
	{
	}

	private string FindCloudObjectName(int cloudId)
	{
		return null;
	}

	private void OpenCloudSmooth(int cloudId)
	{
	}

	private void OnOpenCloudAnimUpdated(float value)
	{
	}

	private void OnOpenCloudAnimCompleted(itDic parameters)
	{
	}

	public void FocusCloud(int cloudId)
	{
	}

	public void UnfocusCloud()
	{
	}

	public void OpenRegion(int cloudId)
	{
	}

	private void OnOpenTown(JsonData response)
	{
	}

	public void AllOpenTown(List<int> townIds, List<int> newCloudIds)
	{
	}

	public void RefreshTown(int townId)
	{
	}

	private void AllocateTown(int townId)
	{
	}

	private void UpdateTownObj(GameObject townObj, int townId)
	{
	}

	public void UpdateAllTownTaxIcons()
	{
	}

	private void UpdateTaxIcons(Transform town)
	{
	}

	public void DyeTownColor(MeshRenderer renderer, Color32 color)
	{
	}

	public void ClickTown(GameObject townObj, int townId)
	{
	}

	public void ClickTown(GameObject townObj, int townId, int desireZoomStep)
	{
	}

	private void RefreshTown(object sender, EventArgs args)
	{
	}

	public void FocusTown(int curTown)
	{
	}

	private void _FocusTown(int curTown)
	{
	}

	public void UnfocusTown()
	{
	}

	private void BlinkTown()
	{
	}

	private void OnBlinkTownUpdated(float value)
	{
	}

	public void SetCapitalMarker(int befTownId, int aftTownId)
	{
	}

	public void ShowGuildMain(bool turnOn, bool forcing = false)
	{
	}

	public void ShowBattleDeckUI(bool turnOn, bool forcing = false)
	{
	}

	public void HarvestTownGoods()
	{
	}

	private void OnHarvestTown(JsonData response)
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowHarvestEffect(int townId, int addFood, int addSilver)
	{
		return null;
	}

	public void SetWorldMapStep(WorldMapStep step)
	{
	}

	private bool didOccupyTown()
	{
		return false;
	}

	private bool didCameFromSiegeWin()
	{
		return false;
	}

	private bool didCameFromInstanceWin()
	{
		return false;
	}

	[DebuggerHidden]
	private IEnumerator ShowBattleResult(int townId, int instId)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowSiegeWinEffect(int townId)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowInstanceBossWinEffect(int instId)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowTownLostEffect()
	{
		return null;
	}

	private void GetWorldMapInfo()
	{
	}

	public void OnRequestWorldmapInfo(JsonData response)
	{
	}

	public void ShowInstance(bool willFocusTown = true)
	{
	}

	public void OnCheckInstancesWithoutFocusingTown(JsonData response)
	{
	}

	private void OnCheckInstances(JsonData response)
	{
	}

	private void OnCheckInstances(JsonData response, bool willFocusTown)
	{
	}

	public void ShowInstanceResult(bool willFocusTown, bool waitWorldMapUIActive = false)
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateInstances(bool willFocusTown, bool waitWorldMapUIActive)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator FocusToControlTown()
	{
		return null;
	}

	public void AutoShowHistoryPanel()
	{
	}

	private void WorldMapStandBy()
	{
	}

	private void RemoveMatchUp(int townId)
	{
	}

	public void SwitchMode()
	{
	}

	private void CloseLobby()
	{
	}

	public void ToggleTacticMap()
	{
	}

	private void UpdateWorldMapElementByZoomStep()
	{
	}

	private void ZoomStepChanged(int zoomStep)
	{
	}

	public void ShowBattleField()
	{
	}

	private void ResponseGetBattleFieldInfo(JsonData response)
	{
	}

	public void ClickBattleField(GameObject townObj, int battleFieldId, bool isShowPanel = true)
	{
	}

	public void ShowBattleFieldFade()
	{
	}

	private void AllocateBattleField()
	{
	}
}
