using System;
using System.Collections.Generic;
using LitJson;

public class BattleList
{
	private List<BattleData> m_battleDatas;

	// C# has no syntax for parameterized property 'Item'.
	public BattleData get_Item(int index)
	{
		return null;
	}

	public int Count
	{
		get
		{
			return 0;
		}
	}

	public int townID { get; private set; }

	public int silverLoss { get; private set; }

	public int silverGain { get; private set; }

	public int foodLoss { get; private set; }

	public int foodGain { get; private set; }

	public int totalHonor { get; private set; }

	public int honor { get; private set; }

	public int winCount { get; private set; }

	public int loseCount { get; private set; }

	public BattleResultType lastResult { get; private set; }

	public DateTime lastBattleDate { get; private set; }

	public bool win
	{
		get
		{
			return false;
		}
	}

	public bool haveReward
	{
		get
		{
			return false;
		}
	}

	public BattleList(BattleData battleData)
	{
	}

	public int GotRewards()
	{
		return 0;
	}

	public void Add(BattleData battleData)
	{
	}

	public void Serialize(JsonWriter writer)
	{
	}
}
