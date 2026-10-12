using System;
using System.Collections.Generic;
using Caocao;

public class GuildSiegeWarDefenceController : BattleFieldDefenceController
{
	private GuildPopupSiegeWarFacilityDefSetting m_panelObstacleDefense;

	private Dictionary<int, GuildSiegeDefenceSlotInfo> m_defenceSlot;

	private Dictionary<int, List<IntRect>> m_defenceSlotCoords;

	private List<GuildSiegeDefenceObjInfo> m_defaultDefencePosInfo;

	private List<int> m_pickedObstacleSrls;

	private Dictionary<int, List<GuildSiegeWarDefenceDeck.DefenceObstacleInfo>> m_pickedObstacles;

	private Dictionary<int, UnitData> m_arrangedObstacles;

	private Dictionary<EnumData.GuildSiegeObjectType, Dictionary<int, List<UnitData>>> createdObstacles;

	private UnitData innerCastleUnitData;

	private Dictionary<int, UnitData> m_spawnObjects;

	private int m_obstacleSeqNum;

	private int m_arrangedObstacleId;

	private int arrangedObstacleSubType;

	private ulong npcUid;

	public EnumData.GuildSiegeObjectType controlObjType;

	private int justRemoveUnitId;

	private int justRemoveUnitArrange;

	private Dictionary<EnumData.GuildSiegeObjectType, Dictionary<int, GuildSiegeObjectByLV>> objectByLvDatas;

	private Dictionary<int, UserUnit> npcUserUnits;

	private List<UserUnit> listNpcUserUnits;

	private Dictionary<int, IntVector2> npcUnitId2DataId;

	private int m_spawnerSetId;

	private int m_spcSpawnerSetId;

	private IUserDataBase targetUserData;

	private GuildSiegeWarFacilityWar curFacility;

	private GuildSiegeTownFacilityByLV curFacilityByLv;

	private bool initiated;

	private bool[] bfIdxMarks;

	[NonSerialized]
	public List<EventCmdBase> defenceGuildSiegeDeckCmds;

	private UnitSide targetUnitSide;

	private List<UnitData> pickedUnitList;

	private List<UserUnit> listMyUnits;

	public GuildPopupSiegeWarFacilityDefSetting panelObstacleDefense
	{
		get
		{
			return null;
		}
	}

	public Dictionary<int, GuildSiegeDefenceSlotInfo> defenceSlot
	{
		get
		{
			return null;
		}
	}

	public Dictionary<int, List<IntRect>> defenceSlotCoords
	{
		get
		{
			return null;
		}
	}

	public List<GuildSiegeDefenceObjInfo> defaultDefencePosInfo
	{
		get
		{
			return null;
		}
	}

	public int defenceUnitNum
	{
		get
		{
			return 0;
		}
	}

	public int defenceTowerNum
	{
		get
		{
			return 0;
		}
	}

	public int defenceBarracksNum
	{
		get
		{
			return 0;
		}
	}

	public int defenceObstacleNum
	{
		get
		{
			return 0;
		}
	}

	public int unitMaxNum { get; protected set; }

	public int towerMaxNum { get; protected set; }

	public int barracksMaxNum { get; protected set; }

	public int obstacleMaxNum { get; protected set; }

	public List<int> pickedObstacleSrls
	{
		get
		{
			return null;
		}
	}

	public Dictionary<int, List<GuildSiegeWarDefenceDeck.DefenceObstacleInfo>> pickedObstacles
	{
		get
		{
			return null;
		}
	}

	public Dictionary<int, UnitData> arrangedObstacles
	{
		get
		{
			return null;
		}
	}

	public Dictionary<int, UnitData> spawnObjects
	{
		get
		{
			return null;
		}
	}

	public int arrangedObstacleId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public override int arrangedUnitId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int pickedTowerNum { get; private set; }

	public int pickedBarracksNum { get; private set; }

	public int pickedObstacleNum { get; private set; }

	protected override string errMsgOtherDefenceGroup
	{
		get
		{
			return null;
		}
	}

	public int spawnerSetId
	{
		get
		{
			return 0;
		}
	}

	public int spcSpawnerSetId
	{
		get
		{
			return 0;
		}
	}

	public override bool GetPickedDefenseUnitNum(int groupId, out int num)
	{
		num = default(int);
		return false;
	}

	public override int GetDefUnitMinNum()
	{
		return 0;
	}

	public override int GetDefUnitMaxNum()
	{
		return 0;
	}

	public override bool IsChangeDefenseDeck()
	{
		return false;
	}

	public override string GetChangeEquippedItemDefenseUnit(bool isExiting)
	{
		return null;
	}

	public void ClearSlotInfo()
	{
	}

	public void AddSlotInfo(GuildSiegeDefenceSlotInfo slotInfo)
	{
	}

	public void AddSlotCoordInfo(int slotNum, List<IntRect> coords)
	{
	}

	public void AddDefaultPosInfo(GuildSiegeDefenceObjInfo defPosInfo)
	{
	}

	public int GetBfGroup(int slotNum)
	{
		return 0;
	}

	public override int? GetBFIndex(int unitId)
	{
		return null;
	}

	public override int GetBFIndex(ReconSlot reconSlot)
	{
		return 0;
	}

	public override int GetGroupSlotNum(int bfIndex)
	{
		return 0;
	}

	public bool GetDefenceUnitOriginalPos(UnitData unitData, out IntVector2 origPos)
	{
		origPos = default(IntVector2);
		return false;
	}

	public UnitData FindObstacle(int bfIndex)
	{
		return null;
	}

	protected override BattleFieldDefenseUnit GenerateDefenceUnitInfo(ReconSlot reconSlot, int bfGroup, int bfIdx, int unitId, ulong weapon, ulong armor, ulong kit, int skinItemId)
	{
		return null;
	}

	protected GuildSiegeWarDefenceDeck.DefenceObstacleInfo GenerateDefenceObstacleInfo(ReconSlot reconSlot, int bfGroup, int bfIdx, int srl, EnumData.GuildSiegeObjectType objType, int subType)
	{
		return null;
	}

	protected override void RemovePicked(int unitId)
	{
	}

	private void UpdateObstacleNum(EnumData.GuildSiegeObjectType objType, int addCount)
	{
	}

	protected override void RemovePicked(UnitData unitData)
	{
	}

	public override void UnPickAll()
	{
	}

	public override void UnPick(int unitId)
	{
	}

	public override void CancelMoveUnitArrange(UnitData srcUnitData)
	{
	}

	public override void MoveUnitArrange(UnitData srcUnitData)
	{
	}

	public override void WriteDefenseUnitDecks(List<object> args)
	{
	}

	public override void Init()
	{
	}

	public override void PickInitialUnits()
	{
	}

	public GuildSiegeObjectByLV ObstacleTypeToUnitData(EnumData.GuildSiegeObjectType objType, int subType)
	{
		return null;
	}

	public Dictionary<int, GuildSiegeObjectByLV> GetObjTypeUnitLvDatas(EnumData.GuildSiegeObjectType objType)
	{
		return null;
	}

	public void SetObstacleSubType(GuildSiegeWarDefenceDeck.DefenceObstacleInfo obsInfo, int objByLvId, int subTypeIdx)
	{
	}

	public void InitiateData()
	{
	}

	public bool IsMarkedBFIndex(int bfIndex)
	{
		return false;
	}

	public void SetAutoObstacleDeck()
	{
	}

	public override void SetAutoDefenceDeck(bool reset)
	{
	}

	public void ArrangePickedUnits()
	{
	}

	private List<UnitData> GetPickedUnitList()
	{
		return null;
	}

	public void ApplyBattleFieldUnitPassives()
	{
	}

	private void ApplyBattleRulePassives(BattleRule battleRule, UnitData unitData)
	{
	}

	private void ApplyBattleRulePassives(BattleRule battleRule, List<UnitData> units)
	{
	}

	private bool ArrangeUnit(GuildSiegeWarDefenceDeck.DefenceUnitInfo unitInfo)
	{
		return false;
	}

	private UnitData ArrangeDefenceUnit(IntVector2 unitPos, UserUnit unit, int bfIdx, int bfGroup, UnitSide targetUnitSide, EventCmd636 cmd, GuildSiegeWarDefenceDeck.DefenceUnitInfo unitInfo)
	{
		return null;
	}

	private EventCmd636 GetCmd636(int slotNum)
	{
		return null;
	}

	private bool ArrangeObstacle(GuildSiegeWarDefenceDeck.DefenceObstacleInfo obsInfo)
	{
		return false;
	}

	private UnitData FindUnitOnGrid(IntVector2 pos)
	{
		return null;
	}

	private UnitData ArrangeDefenceObstacle(int unitId, UnitSide targetUnitSide, IntVector2 obsPos, int bfIdx, int bfGroup, EventCmd636 cmd636, GuildSiegeObjectByLV lvData, GuildSiegeWarDefenceDeck.DefenceObstacleInfo obsInfo)
	{
		return null;
	}

	private void ApplyObstaclePassive(UnitData unitData, EnumData.GuildSiegeObjectType objType, int subType)
	{
	}

	public void ArrangeInnerCastle(GuildSiegeDefenceSlotInfo slotInfo, IntVector2 coord, int bfIdx, UnitDirection direct, int aiType, TargetInfo aiTargetInfo, IntVector2 aiTargetPos)
	{
	}

	public void ApplyPassive(UnitData unitData, GuildSiegeObjectByLV objByLv, EnumData.GuildSiegeObjectType obejectType, bool applyBattleRulePassives)
	{
	}

	public UnitData ArrangeObject(int unitId, int unitLv, string unitName, IntVector2 coord, int bfIdx, UnitDirection direct, int aiType, TargetInfo aiTargetInfo, IntVector2 aiTargetPos, UnitSide unitSide)
	{
		return null;
	}

	public override void ReadyArrangeUnit()
	{
	}

	public void SetObjControlType(EnumData.GuildSiegeObjectType objType)
	{
	}

	public override bool IsEquipmentWearable(UserUnit unit)
	{
		return false;
	}

	public override List<UserUnit> GetUserUnits()
	{
		return null;
	}

	public override bool IsOtherDefenceGroupUnit(int unitId)
	{
		return false;
	}

	public override UserUnit GetUserUnitFromID(int unitId)
	{
		return null;
	}

	public override bool IsHaveUnit(int unitId)
	{
		return false;
	}

	public UserUnit GetArrangedUserUnit(int arrangeUnitId)
	{
		return null;
	}

	public void ClosePopupFacilityInfo()
	{
	}

	public bool IsPickedFacility(int facilitySrl)
	{
		return false;
	}

	public bool IsPickedFacility(UserUnit facilityUnit)
	{
		return false;
	}

	public UnitData GetObstacleUnitFromID(int obstacleUnitSrl)
	{
		return null;
	}

	public void CancelArrangeFacility(int seqFacilityId)
	{
	}

	private List<UnitData> GetCreatedObstacleList(EnumData.GuildSiegeObjectType objType, int subType)
	{
		return null;
	}

	private UnitData GetUnusedObstacleUnitData(EnumData.GuildSiegeObjectType objType, int subType, out List<UnitData> lst)
	{
		lst = null;
		return null;
	}

	public void ReadyArrangeObstacle(int unitId, EnumData.GuildSiegeObjectType controlObjType, int subType)
	{
	}

	public bool IsPicked(EnumData.GuildSiegeObjectType objType, int bfGroup, int bfIdx)
	{
		return false;
	}

	public override bool TryPick(int unitId)
	{
		return false;
	}

	public override void ClickArrangedUnit(UnitData unitData)
	{
	}

	public override void Picked(UserUnit userUnit, ReconSlot reconSlot)
	{
	}

	public List<UnitData> GetObstacleByObjectType(EnumData.GuildSiegeObjectType objectType)
	{
		return null;
	}
}
