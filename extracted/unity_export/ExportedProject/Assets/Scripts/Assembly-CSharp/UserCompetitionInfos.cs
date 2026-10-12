using System.Collections.Generic;
using LitJson;

public class UserCompetitionInfos : ITransmittable, iTransmitContainer
{
	private SortedDictionary<string, UserCompetitionInfo> m_competitionInfos;

	public SortedDictionary<string, UserCompetitionInfo> competitionInfos
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserCompetitionInfo get_Item(string limitDay)
	{
		return null;
	}

	public UserCompetitionInfo getLatest
	{
		get
		{
			return null;
		}
	}

	public UserCompetitionInfo getPrev
	{
		get
		{
			return null;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void AddCompetitionInfo(UserCompetitionInfo seasonCompetitionInfo)
	{
	}
}
