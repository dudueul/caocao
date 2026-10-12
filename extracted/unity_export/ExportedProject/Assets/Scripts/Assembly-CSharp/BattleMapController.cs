using System.Collections;
using System.Diagnostics;
using Caocao;
using LitJson;
using UnityEngine;

public class BattleMapController : MonoBehaviour
{
	public MapBorder mapBorder;

	public int battlemapID { get; private set; }

	private void Start()
	{
	}

	private bool CheckExpBoost()
	{
		return false;
	}

	private void OnPayExpBoost(JsonData response)
	{
	}

	private void StartInitialize()
	{
	}

	private void StartImpl()
	{
	}

	private void OnApplicationPause(bool pauseStatus)
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnBackButton()
	{
	}

	private void OnDestroy()
	{
	}

	public void LoadEvent()
	{
	}

	[DebuggerHidden]
	private IEnumerator _LoadEvent_()
	{
		return null;
	}

	public void SetBattleMapTexture(int mapId)
	{
	}

	public void LoadEventForRecon()
	{
	}

	[DebuggerHidden]
	private IEnumerator _LoadEventForRecon()
	{
		return null;
	}

	public void LoadBattleFieldForRecon()
	{
	}

	[DebuggerHidden]
	private IEnumerator _LoadBattleFieldForRecon()
	{
		return null;
	}

	private void LoadBattleFieldForGuildSiegeWar()
	{
	}

	[DebuggerHidden]
	private IEnumerator _LoadBattleFieldForGuildSiegeWar()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator DoItAfterAllStartFuncCalled()
	{
		return null;
	}

	public void StartBattle()
	{
	}

	private void SendStartBattleRequest()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitForCompleteArenaSetting(string clientUnits)
	{
		return null;
	}

	private void OnWorldbossBattleStart(JsonData response)
	{
	}

	private void OnBattleStart(JsonData response)
	{
	}

	public static UnitSide CaptureBattleMapObjects(UnitSide unitSide, Vector3 pos)
	{
		return UnitSide.OWN;
	}

	[DebuggerHidden]
	private IEnumerator WaitForBattleStart()
	{
		return null;
	}

	private void CheckCommonMission()
	{
	}
}
