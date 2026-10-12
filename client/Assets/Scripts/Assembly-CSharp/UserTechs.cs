using System;
using System.Collections.Generic;
using LitJson;

public class UserTechs : ITransmittable
{
	private Dictionary<int, UserTech> m_id2Techs;

	private List<int> m_ids;

	private UserTechLocalInfos m_localInfos;

	public Action deserializeCallback;

	private UserTechLocalInfos localInfos
	{
		get
		{
			return null;
		}
	}

	public Dictionary<int, UserTech> id2Techs
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserTech get_Item(int id)
	{
		return null;
	}

	[DoNotJsonfy]
	public List<int> ids
	{
		get
		{
			return null;
		}
	}

	public bool IsNewTagDeactivated(int techId, int checkLevel)
	{
		return false;
	}

	public bool SetNewTagDeactivation(int techId, int currLevel)
	{
		return false;
	}

	public static string GetLocalInfoKey()
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
