using System;
using System.Collections.Generic;
using LitJson;

public class GuildMembers : GuildDependentDataBase
{
	private Dictionary<ulong, GuildMember> m_id2Member;

	private List<ulong> m_ids;

	public Dictionary<ulong, GuildMember> id2Member
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildMember get_Item(ulong id)
	{
		return null;
	}

	public List<ulong> ids
	{
		get
		{
			return null;
		}
	}

	public int Count
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	public Action deserializeCompetitionInfoCallback { get; set; }

	public bool needDetailInfo
	{
		get
		{
			return false;
		}
	}

	public GuildMembers(GuildData guildData)
		: base(null)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void Deserialize(JsonData memberData, JsonData detailsData)
	{
	}

	public void DeserializeDonateInfo(JsonData jsonData)
	{
	}

	public void DeserializeCompetitionInfo(JsonData jsonData)
	{
	}

	public void Regist(GuildMember member)
	{
	}

	public GuildMember RegistNpc(ulong uid, ulong guildId)
	{
		return null;
	}

	public void Clear()
	{
	}

	public void RequestMembersDetailInfo(Action successCallback, Action failCallback)
	{
	}

	public GuildSiegeWarSupportDeck GetSupportDeck(ulong uid)
	{
		return null;
	}

	public void SetSupportDeck(GuildSiegeWarSupportDeck deck)
	{
	}

	public void SetSiegeWatAttackDeck(GuildSiegeWarAttackDeck deck)
	{
	}

	public GuildSiegeWarAttackDeck GetSiegeWarAttackDeck(ulong uid)
	{
		return null;
	}

	public List<GuildMember> GetHasDefenceDeckMembers()
	{
		return null;
	}
}
