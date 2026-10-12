using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserTowns : ITransmittable, iTransmitContainer
{
	private Dictionary<ObscuredInt, UserTown> m_id2Towns;

	private List<ObscuredInt> m_ids;

	private Dictionary<int, UserTown> m_mayorUnitId2Towns;

	public Dictionary<ObscuredInt, UserTown> id2Towns
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserTown get_Item(int id)
	{
		return null;
	}

	[DoNotJsonfy]
	public List<ObscuredInt> ids
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public int myTownCount
	{
		get
		{
			return 0;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void DeserializeMatchUps(JsonData jsonData)
	{
	}

	public MatchUp DeserializeMatchUp(JsonData matchUpData)
	{
		return null;
	}

	public void RemoveMatchUp(int townId)
	{
	}

	public void DeserializeShamInfos(JsonData jsonData)
	{
	}

	public int GetCapitalTownId()
	{
		return 0;
	}

	public UserTown GetCapital()
	{
		return null;
	}

	public bool IsMayorUnit(int unitId)
	{
		return false;
	}

	public UserTown GetTownWithMayorUnit(int unitId)
	{
		return null;
	}

	public int GetSumTownFunction(EnumData.TownBuildingType type)
	{
		return 0;
	}

	public bool HasAnyTownFunctionBonus(EnumData.TownBuildingType type)
	{
		return false;
	}

	public bool IsBorder(int townId)
	{
		return false;
	}

	public void GetLostTowns(ref List<int> lost, ref List<int> newly)
	{
	}

	public List<int> GetMyTownIDs()
	{
		return null;
	}

	public List<int> GetMyTownIDs(int stateID)
	{
		return null;
	}

	public int GetMyCastleCount(int stateID)
	{
		return 0;
	}

	public int GetMyCastleCount()
	{
		return 0;
	}

	public List<int> GetMyCastleIDs()
	{
		return null;
	}

	public int GetTownSoldierDeckId(int townId)
	{
		return 0;
	}

	public List<int> GetAssignedSoldierDeckTownList(int deckId)
	{
		return null;
	}

	public List<int> GetSearchedClouds()
	{
		return null;
	}

	public int GetTownBuildingLevel(int townId, EnumData.TownBuildingType type)
	{
		return 0;
	}

	private void GetTowns(ref List<int> myLastTowns, ref List<int> lostTowns)
	{
	}

	private void SaveTowns(List<int> myTowns, List<int> lostTowns)
	{
	}

	public List<int> GetPopularityRewardReceivableTownIds()
	{
		return null;
	}

	public int GetPopularityRewardReceivableCount()
	{
		return 0;
	}

	public void UpdatePopularity(int deltaPopularity)
	{
	}

	public string GetNationPopularityGradeString()
	{
		return null;
	}

	public int GetNationPopularityGrade(int popularityAdd = 0)
	{
		return 0;
	}

	public int GetMaxPopularityRecoverAvailableCount(EnumData.PopularityRecoveryType recoverType)
	{
		return 0;
	}

	public int GetTotalPopularityRecoverCost(EnumData.PopularityRecoveryType recoverType, int count)
	{
		return 0;
	}

	public int[] GetCostsByRecoverType(EnumData.PopularityRecoveryType recoverType, int maxCount)
	{
		return null;
	}

	public int GetCurrentPopularityRecoveredCount(EnumData.PopularityRecoveryType recoverType)
	{
		return 0;
	}

	public int GetMaximumPopularityRecoverCount(EnumData.PopularityRecoveryType recoverType)
	{
		return 0;
	}

	public bool CheckPopularityRewardReceivedToday()
	{
		return false;
	}
}
