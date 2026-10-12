using System;
using System.Collections.Generic;
using LitJson;

public class GuildSiegeWarFacilities : GuildDependentDataBase
{
	private Dictionary<int, GuildSiegeWarFacility> m_id2Facility;

	private List<int> m_ids;

	public Dictionary<int, GuildSiegeWarFacility> id2Facility
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildSiegeWarFacility get_Item(int id)
	{
		return null;
	}

	public List<int> ids
	{
		get
		{
			return null;
		}
	}

	public int Count
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	public GuildSiegeWarFacilities(GuildData guildData)
		: base(null)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void Clear()
	{
	}

	public bool IsValid()
	{
		return false;
	}
}
