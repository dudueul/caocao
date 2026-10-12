using System.Collections.Generic;
using GuildWar;

public class UIGuildWarPassiveData
{
	public GuildWarPassive p_passive;

	public GuildWarPassiveAttribute p_passiveAttribute;

	public int passiveValue;

	public List<GuildWarPassiveList> p_passiveLists;

	public List<bool> enables;

	public List<CorpsPassiveData> corpsPassiveDatas;

	public List<BuildingPassiveData> buildingPassiveDatas;

	public int lastEnabledIndex;
}
