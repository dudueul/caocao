using LitJson;

public class GuildSiegeWarSupportDeck : UserUnitGroup
{
	private ulong m_userId;

	private ulong m_guildId;

	public ulong userId
	{
		get
		{
			return 0uL;
		}
	}

	public ulong guildId
	{
		get
		{
			return 0uL;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
