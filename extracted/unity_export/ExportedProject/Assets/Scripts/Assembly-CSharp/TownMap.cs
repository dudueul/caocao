using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class TownMap : MonoBehaviour
{
	public Transform m_castle;

	public Transform m_foodStorage;

	public Transform m_safe;

	public Transform m_silverStorage;

	public Transform m_foodPlant;

	public Transform m_silverPlant;

	public GameObject m_gateN;

	public GameObject m_gateS;

	public GameObject m_gateE;

	public GameObject m_gateW;

	private Town m_town;

	private UserTown m_myTown;

	private Dictionary<EnumData.TownBuildingType, Transform> m_buildings;

	private void OnDestroy()
	{
	}

	public void InitTownMap(int townId)
	{
	}

	public void UpgradeBuilding(EnumData.TownBuildingType buildingType, int curLev)
	{
	}

	private string GetBottomTextDescString(EnumData.TownBuildingType buildingType, int prevLevel, int curLevel)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowUpgradeEffect(EnumData.TownBuildingType buildingType)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowUpgradeEffectShort(EnumData.TownBuildingType buildingType)
	{
		return null;
	}

	public void UpdateBuildingLook(EnumData.TownBuildingType buildingType)
	{
	}

	private void SetGates()
	{
	}

	private void SetGateSprite(int stepLevel)
	{
	}

	private void SetSprite(GameObject gate, int gateDir, int townGrade, int baseType)
	{
	}

	private void SetInterior()
	{
	}
}
