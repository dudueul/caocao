using System.Collections.Generic;

public class TreasureEnhancePassiveCombinedData
{
	private int m_treasureEnhancePassiveId;

	private int[] m_value;

	private int[] m_rate;

	private List<int> m_tile;

	private List<int> m_condition1;

	private List<int> m_condition2;

	public int TreasureEnhancePassiveId
	{
		get
		{
			return 0;
		}
	}

	public TreasureEnhancePassiveCombinedData(int treasureEnhancePassiveId)
	{
	}

	public void AppendInfo(PassiveList passiveList, TreasureEnhancePassive enhancePassive)
	{
	}

	public string GetName()
	{
		return null;
	}

	public string GetDesc()
	{
		return null;
	}

	public string GetMaxDesc(bool shortDesc = false)
	{
		return null;
	}
}
