using System.Collections.Generic;
using Caocao;
using Thingsoft.Pool;

public class TroopMan : SceneSingleton<TroopMan>
{
	private static readonly List<UnitData> ms_emptyUnitDataList;

	private List<UnitData> m_unitDataList;

	private Dictionary<UnitSide, List<UnitData>> m_side2Units;

	private Dictionary<ulong, UnitData> m_srl2Unit;

	private Dictionary<int, List<UnitData>> m_unitID2Units;

	private Dictionary<uint, UnitData> m_index2Unit;

	private Dictionary<ulong, UnitSide> m_uid2Side;

	private uint unitIndex;

	public List<UnitData> allUnitData
	{
		get
		{
			return null;
		}
	}

	protected TroopMan()
	{
	}

	public void RegisterUnit(UnitData unitData)
	{
	}

	public void ChangeUnitSide(UnitData unitData, UnitSide unitSide)
	{
	}

	public void UnregisterUnit(UnitData unitData, bool destroy = true)
	{
	}

	protected override void OnDestroy()
	{
	}

	public List<UnitData> GetOrderedUnits(UnitSide unitSide)
	{
		return null;
	}

	public UnitData GetUnitByIndex(uint unitIndex)
	{
		return null;
	}

	public List<UnitData> GetUnitsBySide(UnitSide unitSide)
	{
		return null;
	}

	public List<List<UnitData>> GetUnitsByGroup(Caocao.UnitGroup unitGroup, List<List<UnitData>> groupUnit = null)
	{
		return null;
	}

	public UnitData GetUnitBySrl(ulong srl)
	{
		return null;
	}

	public List<UnitData> GetUnitsBySides(List<UnitSide> unitSides)
	{
		return null;
	}

	public void GetUnitsBySides(UnitSides unitSides, Thingsoft.Pool.UnitData res)
	{
	}

	public bool IsExist(ulong srl)
	{
		return false;
	}

	public List<UnitData> GetUnitID2Units(int unitID)
	{
		return null;
	}

	public UnitData GetUnitID2Unit(int unitID, string nickName, UnitSide side)
	{
		return null;
	}

	public UnitData GetUnitID2Unit(int unitID, string sNickName)
	{
		return null;
	}

	public int GetUnitTotCount(UnitSide side)
	{
		return 0;
	}

	public bool GetTurnCompleted(UnitSide side)
	{
		return false;
	}

	public void GetAliveUnits(List<UnitData> list)
	{
	}

	public void GetAliveUnits(List<UnitData> list, UnitSide unitSide)
	{
	}

	public int GetAliveUnitCnt(UnitSide side)
	{
		return 0;
	}

	public int GetAliveUnitCnt(Caocao.UnitGroup group)
	{
		return 0;
	}

	public int GetDeadUnitCnt(UnitSide side)
	{
		return 0;
	}

	public int GetAvailableUnitCnt(UnitSide side)
	{
		return 0;
	}

	public int GetRetreatUnitCnt(UnitSide side)
	{
		return 0;
	}

	public int GetActionNoCompletedUnitCnt(UnitSide side)
	{
		return 0;
	}

	public int GetActionCompletedUnitCnt(UnitSide side)
	{
		return 0;
	}

	public UnitData GetLeaderUnit(UnitSide side)
	{
		return null;
	}

	public float GetPlunderPct(EnumData.TownBuildingType townBuildingType)
	{
		return 0f;
	}

	public int GetPlunder(EnumData.TownBuildingType townBuildingType)
	{
		return 0;
	}

	public UnitData GetBackUpAliveUnit(int unitId, UnitSide side)
	{
		return null;
	}

	public void OnEndTurn()
	{
	}

	public UnitData FindUnitOnGrid(IntVector2 gridPos)
	{
		return null;
	}

	public void InvalidateAllUnitStats()
	{
	}

	public void UpdateAllSongAura(bool addEffect = false)
	{
	}

	public void RefreshAllUnitAnimation()
	{
	}

	public int CompareOppositeUnitType(UnitSide unitSide)
	{
		return 0;
	}

	public int CountOppositeNoAtkUnits(UnitSide unitSide)
	{
		return 0;
	}

	public int CountOppositeNoArtUnits(UnitSide unitSide)
	{
		return 0;
	}

	public int CountUnitsByUnitType(UnitSide unitSide, EnumData.unitType type)
	{
		return 0;
	}

	public UnitSide GetSideByUserId(ulong uid)
	{
		return UnitSide.OWN;
	}
}
