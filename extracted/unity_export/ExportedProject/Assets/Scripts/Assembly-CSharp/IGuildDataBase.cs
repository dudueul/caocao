public interface IGuildDataBase
{
	GuildInfo info { get; }

	GuildMembers members { get; }

	GuildSiegeWar siegeWar { get; }

	GuildDonations donations { get; }

	void Clear();
}
