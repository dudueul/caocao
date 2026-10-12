using System;
using System.Collections.Generic;
using LitJson;

public class UserSoldiers : ITransmittable, iTransmitContainer
{
	public static ulong ms_srl;

	private Dictionary<ulong, UserSoldier> m_srl2Soldiers;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserSoldier> soldiers
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserSoldier get_Item(ulong srl)
	{
		return null;
	}

	[DoNotJsonfy]
	public List<ulong> srls
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

	public UserSoldier GetUserSoldier(int soldierId)
	{
		return null;
	}

	public int GetUserSoldierLevel(int soldierId)
	{
		return 0;
	}
}
