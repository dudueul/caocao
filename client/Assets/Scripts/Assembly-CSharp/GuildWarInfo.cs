using LitJson;

public class GuildWarInfo : ITransmittable
{
	private GuildWarAttends m_myGuildAttends;

	private GuildWarUserBaseData m_myUser;

	private GuildWarBattleJoins m_myGuildBattleJoins;

	public GuildWarAttends myGuildAttends
	{
		get
		{
			return null;
		}
	}

	public GuildWarUserBaseData myUser
	{
		get
		{
			return null;
		}
	}

	public GuildWarBattleJoins myGuildBattleJoins
	{
		get
		{
			return null;
		}
	}

	protected override void DeserializeImpl(JsonData packet)
	{
	}
}
