using UnityEngine;

public class GuildSiegeWarController : MonoBehaviour
{
	public enum GuildSiegeWarUI
	{
		Lobby = 0,
		Match = 1
	}

	public static GuildSiegeWarUI firstUI;

	public static ulong guildId;

	public static EnumData.GuildSiegeWarOrderType type;

	private void Start()
	{
	}
}
