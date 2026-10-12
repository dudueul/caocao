using System.Collections.Generic;
using System.Xml;
using Caocao;
using UnityEngine;

public class MapLoader : SceneSingleton<MapLoader>
{
	private List<Slot> m_slots;

	private GameObject m_mapObj;

	private GameObject m_units;

	private GameObject m_slotObjs;

	private GameObject m_mapObjectsObj;

	private GameObject m_decos;

	private GameObject m_destinations;

	private GameObject m_alertTiles;

	private XmlDocument m_cachedXmlDoc;

	private List<float> m_randPools;

	public GameObject unitsObj
	{
		get
		{
			return null;
		}
	}

	public GameObject mapObjectsObj
	{
		get
		{
			return null;
		}
	}

	public GameObject slotObj
	{
		get
		{
			return null;
		}
	}

	protected MapLoader()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void Load(int battleMapId)
	{
	}

	private void SetMapInfo(int battleMapId, MapInfo mapInfo)
	{
	}

	private GameObject GetTilesFromXML(int battleMapId)
	{
		return null;
	}

	private GameObject GetMapObjectsFromXML(int battleMapId)
	{
		return null;
	}

	public UnitData InstantiateNewUnit(GameObject parent, IntVector2 deployPos, ulong unitSrl, int unitId, string nick, UnitSide unitSide, int exp, int jobLevel, TroopsGrade troopsGrade, int aiType, int levelCorrect, int aiPriorityCorrect, bool hiddenByEvent, int bfIndex, int group, UnitDirection eFaceDirection, IntVector2 unitSize, bool isMapObjectUnit, TargetInfo aiTargetUnitInfo, IntVector2 aiTargetPos, int npcTierLevel)
	{
		return null;
	}

	private UserUnit InstantiateUserUnit(UnitSide unitSide, ulong unitSrl, int unitId, int jobLevel, int exp, int npcTierLevel, string nick)
	{
		return null;
	}

	public void PlaceOwnUnitToSlotImpl(GameObject mapObj, UnitData unit, Slot targetSlot)
	{
	}

	private XmlDocument GetXMLDocument(int battleMapId)
	{
		return null;
	}

	private void GetEnvFromXML(int battleMapId)
	{
	}

	public UnitData UnitSpawn(int unitId, string nick, UnitSide unitSide, bool hiddenByEvent, TroopsGrade troopsGrade, IntVector2 deployPos, int bfIndex, UnitDirection direction, int level, int quality, int aiType, TargetInfo aiTargetUnitInfo, IntVector2 aiTargetPos, bool isSoldier, bool isOwnNPC, bool isMapObjectUnit, IntVector2 unitSize, GameObject parent, bool forceAbsoluteLevel, int bfGroup = 0)
	{
		return null;
	}

	public GameObject AddDestination(IntVector2 pos)
	{
		return null;
	}

	public GameObject AddAlertTile(IntVector2 pos)
	{
		return null;
	}

	private GameObject AddTileSprite(IntVector2 pos, GameObject parent, Sprite sprite)
	{
		return null;
	}

	public Transform GetDestination(IntVector2 pos)
	{
		return null;
	}

	public Transform GetAlertTile(IntVector2 pos)
	{
		return null;
	}

	public void RemoveDestnation(IntVector2 pos)
	{
	}

	public void ClearDestination()
	{
	}

	private int GetAdjustedLevelAvg(int battleMapMinLv, int battleMapMaxLv, int mapLvCorrect, int battleMapMinLvH, int battleMapMaxLvH, int mapLvCorrectH, int mapLvCorrectC, int classicMyLv)
	{
		return 0;
	}

	private int GetAdjustedMaxLv(int adjustedAvrgLv, int battleMapMaxLv, int battleMapMinLv)
	{
		return 0;
	}

	private List<int> GetDeckLevels()
	{
		return null;
	}

	private int GetLevelCorrected(int DecklLevelAvg, int levelCorrect, int jobType)
	{
		return 0;
	}

	private int GetDeckLevelsAvg(int conditionLevel = 0)
	{
		return 0;
	}

	private float GetRandomOffset()
	{
		return 0f;
	}

	public UnitData InstantiateArrangedUnit(IntVector2 deployPos, ulong unitSrl, int unitId, string nick, TroopsGrade troopsGrade, UnitDirection eFaceDirection, IntVector2 unitSize, int bfIndex, int group)
	{
		return null;
	}
}
