using System;
using System.Collections.Generic;
using LitJson;

public class UserTimeBoxes : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserTimeBox> m_srl2Boxes;

	private Dictionary<int, UserTimeBox> m_index2Boxes;

	private List<ulong> m_srls;

	[DoNotJsonfy]
	public List<UserTimeBox> boxes
	{
		get
		{
			return null;
		}
	}

	public int normalSlotCount
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	public UserTimeBox GetBoxByIndex(int index)
	{
		return null;
	}

	public bool IsPurchaseSlot(int slotIndex)
	{
		return false;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public TimeSpan GetShortestRemainTime(bool exceptFromMail = true)
	{
		return default(TimeSpan);
	}

	public int GetNormalBoxCount()
	{
		return 0;
	}
}
