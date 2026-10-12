using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public static class ArenaUtil
{
	private static List<UserUnit> pickedUnits;

	public static void SetEnemyNpcUnits(UserArenaLevel userArenaLevel, List<UserUnit> storage = null)
	{
	}

	public static List<UnitData> GetEnemyNpcUnitList()
	{
		return null;
	}

	public static int[] GetEnemyNpcUnitIdOrder()
	{
		return null;
	}

	public static int GetEnemyFaceUnitId(int level)
	{
		return 0;
	}

	public static bool CheckNeedDecideSupply(Action completeCallback = null)
	{
		return false;
	}

	public static bool CheckNeedDecideScenario()
	{
		return false;
	}

	public static bool CheckEndArena(Action endCallback, Action endByAdminCallback)
	{
		return false;
	}

	public static string GetGlobalPassiveTypeDesc(EnumData.ArenaGloalPassiveType passiveType)
	{
		return null;
	}

	public static void SetTileInfoUI(Transform objTileInfo, ArenaStage pdStage)
	{
	}

	public static void SetWeatherUI(Transform[] objWeathers, ArenaStage pdStage)
	{
	}

	public static void SetGlobalPassiveUI(Transform trsPassiveGrid, ArenaStage pdStage, bool showTooltip, GameObject childPrefab = null, GameObject dimObj = null)
	{
	}

	public static bool CheckHttpResponse(JsonData response)
	{
		return false;
	}

	public static void UpdateRewardTimeBoxUI(ArenaLevel pdArenaLevel, UITexture boxImage, UILabelLocalized boxName)
	{
	}

	public static void CheckLockPassiveEquip(ulong passiveSrl, Action equipCallback)
	{
	}

	public static void CheckAccumPassiveEquip(ulong passiveSrl, ulong unitSrl, int passiveIndex, Action equipCallback)
	{
	}

	public static void CheckLockItemEquip(ulong itemSrl, Action equipCallback)
	{
	}

	public static void SaveAllUnitStartStatus()
	{
	}

	public static void UpdateUnitsBfIndex()
	{
	}

	public static List<UserUnit> GetPickedUnits()
	{
		return null;
	}

	public static bool CheckApplySynergy(UserUnit unit, ArenaSynergyList synergyList)
	{
		return false;
	}

	public static bool CheckSynergyType(UserUnit unit, ArenaSynergyList synergyList)
	{
		return false;
	}

	public static void ShowArenaUnitInfoPopup(int unitId, int weaponTre, int armorTre, int kitTre, List<int> arenaPassiveIds, ArenaLevel pdArenaLevel)
	{
	}

	public static void ShowSelectedUnit(int unitId, UserArenaLevel userArenaLevel, Action callback)
	{
	}

	public static void ShowSelectedBonus(int bonusId, Action callback)
	{
	}

	public static void ResetPrevUnits()
	{
	}
}
