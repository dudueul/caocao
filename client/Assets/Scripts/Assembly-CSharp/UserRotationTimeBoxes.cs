using System;
using System.Collections.Generic;
using LitJson;

public class UserRotationTimeBoxes : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserRotationTimeBox> m_srl2Boxes;

	private List<ulong> m_srls;

	public List<UserRotationTimeBox> boxes
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public TimeBox GetRotationTimeBoxPdata()
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
