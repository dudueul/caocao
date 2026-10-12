using System;
using LitJson;

public class GuildData : ITransmittable, IGuildDataBase
{
	protected GuildInfo m_info;

	protected GuildMembers m_members;

	protected GuildMembers m_npcs;

	protected GuildSiegeWar m_siegeWar;

	protected GuildDonations m_donations;

	public GuildInfo info
	{
		get
		{
			return null;
		}
	}

	public GuildMembers members
	{
		get
		{
			return null;
		}
	}

	public GuildMembers npcs
	{
		get
		{
			return null;
		}
	}

	public GuildSiegeWar siegeWar
	{
		get
		{
			return null;
		}
	}

	public GuildDonations donations
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public Action deserializeMembersCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void DeserializeSiegeWar(JsonData jsonData)
	{
	}

	public void DeserializeDonation(JsonData jsonData)
	{
	}

	public virtual void Clear()
	{
	}

	public GuildMember GetMember(ulong uid)
	{
		return null;
	}
}
