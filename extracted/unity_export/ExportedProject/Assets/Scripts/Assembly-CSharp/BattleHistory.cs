using System.Collections.Generic;

public class BattleHistory
{
	private List<BattleList> m_battleLists;

	public List<BattleList> battleLists
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BattleList get_Item(int index)
	{
		return null;
	}

	public int townID { get; private set; }

	public BattleData lastBattle
	{
		get
		{
			return null;
		}
	}

	public BattleHistory(BattleData battleData)
	{
	}

	private void AddNewBattleList(BattleData battleData)
	{
	}

	public void Add(BattleData battleData)
	{
	}
}
