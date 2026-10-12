using System;
using System.Collections.Generic;
using BattlePlay;
using LitJson;

public class UserTreasureSockets : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserTreasureSocket> m_srl2Socket;

	private List<ulong> m_srls;

	private Dictionary<ulong, UserTreasureSocket> m_itemSrl2Socket;

	public Action deserializeCallback;

	public Dictionary<ulong, UserTreasureSocket> srl2Socket
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public List<ulong> srls
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserTreasureSocket get_Item(ulong srl)
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public UserTreasureSocket GetSocketByItemSrl(ulong itemSrl)
	{
		return null;
	}

	public List<UserTreasureSocket> GetSocketsInMultiplay(List<BattleUnit> battleUnits)
	{
		return null;
	}
}
