using System;
using System.Collections.Generic;
using LitJson;

public class GuildSiegeWarFacilityWars : GuildDependentDataBase
{
	private Dictionary<EnumData.GuildSiegeFacilitySlot, GuildSiegeWarFacilityWar> m_facilities;

	public Dictionary<EnumData.GuildSiegeFacilitySlot, GuildSiegeWarFacilityWar> facilities
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildSiegeWarFacilityWar get_Item(EnumData.GuildSiegeFacilitySlot slot)
	{
		return null;
	}

	public Action deserializeCallback { get; set; }

	public GuildSiegeWarFacilityWars(GuildData guildData)
		: base(null)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public GuildSiegeWarFacilityWar GetParentFacilityWar(GuildSiegeWarFacilityWar fwar)
	{
		return null;
	}

	public void RevertLoadedMembers()
	{
	}

	public void Clear()
	{
	}

	public bool IsValid()
	{
		return false;
	}

	public GuildSiegeWarFacilityWar GetFacilityById(int facilityId)
	{
		return null;
	}

	public List<GuildSiegeWarFacilityWar> GetMemberLoadedFacilities(ulong memberId, ref List<int> lstSlot)
	{
		return null;
	}

	public void UpdateAllFacilityWars(Action successCallback, Action failCallback)
	{
	}

	public void SetLoadedMembers(Dictionary<int, List<GuildSiegeWarDefenceDeck.UserInfo>> dic, Action successCallback, Action failCallback)
	{
	}
}
