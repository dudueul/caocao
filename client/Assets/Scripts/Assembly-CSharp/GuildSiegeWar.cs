using System;
using System.Collections.Generic;
using LitJson;

public class GuildSiegeWar : GuildDependentDataBase
{
	private GuildSiegeWarFacilities m_facilities;

	private GuildSiegeWarFacilityWars m_facilityWars;

	private GuildSiegeWarStat m_stat;

	public GuildSiegeWarFacilities facilities
	{
		get
		{
			return null;
		}
	}

	public GuildSiegeWarFacilityWars facilityWars
	{
		get
		{
			return null;
		}
	}

	public GuildSiegeWarStat stat
	{
		get
		{
			return null;
		}
	}

	public GuildSiegeWar(GuildData guildData)
		: base(null)
	{
	}

	protected override void DeserializeImpl(JsonData packet)
	{
	}

	public void SetSiegeWarDefaultDeck(JsonData response)
	{
	}

	public void GetSupportLog(ulong lastSrl, int pageNum)
	{
	}

	public void GetMySupportDeck(Action successCallback, Action failCallback)
	{
	}

	public void SetSupportDeck(Action successCallback, Action failCallback)
	{
	}

	public void GetMyAttackDeck(Action successCallback, Action failCallback)
	{
	}

	public void InitAttackDeck(Action successCallback, Action failCallback)
	{
	}

	public void UpdateAttackDeck(GuildSiegeWarAttackDeck.UsedType type, Action successCallback, Action failCallback)
	{
	}

	public void InheritAttackDeck(Action success, Action fail)
	{
	}

	public void GetFacilityDefenceInfo(EnumData.GuildSiegeFacilitySlot facilitySlot, bool forceUpate, Action successCallback, Action failCallback)
	{
	}

	public void GetAllDefenceDecks(ulong warId, Action success, Action fail)
	{
	}

	public void ReconDefenceDeck(ulong opponentGuildId, ulong userId, int slot, Action success, Action fail)
	{
	}

	public void SetDefenceDeck(List<object> param, Action success, Action fail)
	{
	}

	public List<int> GetChangableFacilityIds(GuildSiegeWarFacilityWar fwar)
	{
		return null;
	}
}
