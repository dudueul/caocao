using System.Collections.Generic;
using LitJson;

public class UserCompetitionStatSeasons : ITransmittable, iTransmitContainer
{
	private Dictionary<string, UserCompetitionStatSeason> m_competitionStatSeasons;

	public Dictionary<string, UserCompetitionStatSeason> competitionStatSeasons
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserCompetitionStatSeason get_Item(string limitDay)
	{
		return null;
	}

	public UserCompetitionStatSeason getLatest
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
}
