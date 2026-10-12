using System;
using System.Collections.Generic;
using LitJson;

public class UserHistories : ITransmittable
{
	private Dictionary<int, BattleHistory> m_battles;

	public Dictionary<int, BattleHistory> battleHistory
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
