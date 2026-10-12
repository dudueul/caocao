public class GuildDependentDataBase : ITransmittable
{
	public GuildData guildData { get; protected set; }

	public GuildDependentDataBase(GuildData guildData)
	{
	}
}
