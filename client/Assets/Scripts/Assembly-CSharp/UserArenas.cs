using System;
using System.Collections.Generic;
using LitJson;

public class UserArenas : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserArena> m_srl2Arena;

	private List<ulong> m_srls;

	public Action deserializeCallback;

	private UserArenaUnitGroup emptyUnitGroup;

	public Dictionary<ulong, UserArena> srl2Arena
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
	public UserArena get_Item(ulong srl)
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public UserArena GetCurrentArenaInfo()
	{
		return null;
	}

	public UserArenaLevel GetCurrentArenaLevel()
	{
		return null;
	}

	public UserArenaUnitGroup GetCurrentUnitGroup()
	{
		return null;
	}
}
