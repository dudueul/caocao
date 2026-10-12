using System;
using System.Collections.Generic;
using LitJson;

public class UserTreasureBoxes : ITransmittable
{
	private Dictionary<int, UserTreasureBox> m_id2TreasureBoxes;

	private List<int> m_ids;

	public Action updateCallback;

	public Dictionary<int, UserTreasureBox> id2TreasureBoxes
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
	public UserTreasureBox get_Item(int id)
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void UpdateCallback()
	{
	}

	public bool IsOnCoolTime(int boxId)
	{
		return false;
	}

	public DateTime GetUnlockDateTime(int boxId)
	{
		return default(DateTime);
	}
}
