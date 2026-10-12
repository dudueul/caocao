using System;
using System.Collections.Generic;
using LitJson;

public class GuildApplicants : ITransmittable
{
	private Dictionary<ulong, GuildApplicant> m_id2Applicant;

	private List<ulong> m_ids;

	public Dictionary<ulong, GuildApplicant> id2Applicant
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildApplicant get_Item(ulong id)
	{
		return null;
	}

	public List<ulong> ids
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

	public void Deserialize(JsonData applicantsData, JsonData detailsData)
	{
	}

	public void Clear()
	{
	}
}
