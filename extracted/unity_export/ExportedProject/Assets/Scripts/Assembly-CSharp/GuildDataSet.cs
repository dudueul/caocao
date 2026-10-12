using System.Collections.Generic;
using UnityEngine.SceneManagement;

public class GuildDataSet
{
	private MyGuildData m_myGuildData;

	private Dictionary<ulong, GuildData> m_uid2GuildData;

	public static GuildDataSet instance
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildData get_Item(ulong guildId)
	{
		return null;
	}

	public MyGuildData GetMyGuildData()
	{
		return null;
	}

	public bool HasMyGuildData()
	{
		return false;
	}

	public void DestroyMyGuildData()
	{
	}

	public void Clear()
	{
	}

	public GuildData GetGuildData(ulong guildId)
	{
		return null;
	}

	public GuildInfo TryGetGuildInfo(ulong guildId)
	{
		return null;
	}

	public void Register(GuildData guildData)
	{
	}

	public void Unregister(GuildData guildData)
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnLevelFinishedLoading(Scene scene, LoadSceneMode mode)
	{
	}
}
