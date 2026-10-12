using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class AnnihilationWarEnemy
{
	public enum DifficultyType
	{
		Easy = 0,
		Normal = 1,
		Hard = 2
	}

	private const int npcWinningScore = 5;

	private const int mmrPointRange = 20;

	private const float maxExponent = 5f;

	private List<UserUnit> m_userUnitList;

	private List<UserUnitGroup.UnitInfo> m_unitList;

	public ObscuredULong userId { get; set; }

	public int winningScore
	{
		get
		{
			return 0;
		}
	}

	public DifficultyType difficulty
	{
		get
		{
			return DifficultyType.Easy;
		}
	}

	public int rank { get; set; }

	public int anniPrevRank { get; set; }

	public int tier { get; set; }

	public string nationName { get; set; }

	public int point { get; set; }

	public int mmr { get; set; }

	public string formationName { get; set; }

	public bool isNpc { get; private set; }

	public int combatPower { get; private set; }

	public List<UserUnit> userUnitList
	{
		get
		{
			return null;
		}
	}

	public List<UserUnitGroup.UnitInfo> unitList
	{
		get
		{
			return null;
		}
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	private void DeserializeUnitsData(JsonData jsonData, out Dictionary<int, UserUnit> dicUnits)
	{
		dicUnits = null;
	}

	private void DeserializeItemsData(JsonData jsonData, out Dictionary<ulong, UserItem> dicItems)
	{
		dicItems = null;
	}

	private void DeserializeTreasureEnhancesData(JsonData jsonData, Dictionary<ulong, UserItem> dicItems)
	{
	}

	private void DeserializeRuneSlotsData(JsonData jsonData, Dictionary<int, UserUnit> dicUnits)
	{
	}
}
