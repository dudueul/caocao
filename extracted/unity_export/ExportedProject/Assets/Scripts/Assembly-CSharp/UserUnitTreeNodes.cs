using System;
using System.Collections.Generic;
using LitJson;

public class UserUnitTreeNodes : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserUnitTreeNode> m_id2UnitTreeNode;

	private List<int> m_ids;

	public Dictionary<int, UserUnitTreeNode> id2UnitTreeNode
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public List<int> ids
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserUnitTreeNode get_Item(int srl)
	{
		return null;
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public int GetUnitCountOnSpecific(int setId)
	{
		return 0;
	}

	public int GetUnitCountOnEntire()
	{
		return 0;
	}

	public int GetCountOfOverUnitLevOnSpecific(int setId, int lev)
	{
		return 0;
	}

	public int GetCountOfOverUnitLevOnEntire(int lev)
	{
		return 0;
	}

	public int GetCountOfOverJobLevOnSpecific(int setId, int jobLev)
	{
		return 0;
	}

	public int GetCountOfOverReinforceLevOnSpecific(int setId, int reinforceLev)
	{
		return 0;
	}

	public int GetCountOfOverJobLevOnEntire(int lev)
	{
		return 0;
	}
}
