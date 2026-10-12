using System;
using System.Collections.Generic;
using Caocao;
using CodeStage.AntiCheat.ObscuredTypes;
using Thingsoft.Pool;

public class GuildSiegeWarFlowController : Singleton<GuildSiegeWarFlowController>
{
	private GuildSiegeWarDefenceController m_defenceController;

	private ObscuredInt m_controlFacilityId;

	private ObscuredInt m_controlFacilitySlot;

	private ObscuredULong m_reconUserId;

	private ObscuredULong m_targetGuildId;

	private GuildSiegeWarDefenceDeck m_targetDefenceDeck;

	public bool[] testSubFacilityFall;

	public GuildSiegeWarDefenceController defenceController
	{
		get
		{
			return null;
		}
	}

	public int controlFacilityId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public EnumData.GuildSiegeFacilitySlot controlFacilitySlot
	{
		get
		{
			return EnumData.GuildSiegeFacilitySlot.LeftFortress;
		}
		set
		{
		}
	}

	public ulong targetUserId
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public ulong deckUserId { get; set; }

	public bool isNpc { get; set; }

	public ulong targetGuildId
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public GuildSiegeWarDefenceDeck targetDefenceDeck
	{
		get
		{
			return null;
		}
	}

	public int controlDefenceDeckSlot { get; set; }

	public EnumData.BattleFieldDefenseMode controlMode { get; protected set; }

	public bool supportMode { get; set; }

	public bool initAttackMode { get; set; }

	public int scenarioId { get; protected set; }

	public bool IsCurrentControlBfGroup(int bfGroup)
	{
		return false;
	}

	public void GetUnitsBySlot(UnitSides unitSides, Thingsoft.Pool.UnitData units, int slotNumber, int coordIndex, Thingsoft.Pool.UnitData res)
	{
	}

	public bool GetDefenceUnitOriginalPos(UnitData unitData, out IntVector2 origPos)
	{
		origPos = default(IntVector2);
		return false;
	}

	public void Init()
	{
	}

	public void SetSpawnInfoSlots()
	{
	}

	public void RequestSetDefenceUnits()
	{
	}

	public void GotoShamBattle(int facilityId, EnumData.GuildSiegeFacilitySlot facilitySlot, ulong memberId, Action cancelCallback)
	{
	}

	public void InitBattle(ulong targetGuildId, ulong targetUserId, EnumData.GuildSiegeFacilitySlot facilitySlot, int defenceDeckSlot, int facilityId)
	{
	}

	public void RequestStartSiegeWarSham(HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestGetOpponentData()
	{
	}

	public void ArrangeDefenceDeckForBattle()
	{
	}

	private void RegistDefenceUnitsToTroopMan()
	{
	}

	public void UnregistDefenceUnits()
	{
	}

	private void CopyUserDataForBattle(GuildSiegeWarDefenceDeck defenceDeck)
	{
	}

	public void ArrangeObject(int bfIndex, IntVector2 coord, UnitDirection direct, int aiType, TargetInfo aiTargetInfo, IntVector2 aiTargetPos)
	{
	}

	public List<UnitData> GetDeadDefenceUnitDatas()
	{
		return null;
	}
}
