using UnityEngine;

public class GuildUIMarkName : MonoBehaviour
{
	private ulong user_id_internal;

	public UITexture guildMark;

	public UILabel guildName;

	public UILabel guildLv;

	public ulong user_id
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public void UpdateGuilSummary(GuildInfo oneGuildInfo, GuildMember oneGuildMember)
	{
	}

	private void UpdateUI(GuildInfo oneGuildInfo)
	{
	}

	public void Hide()
	{
	}
}
