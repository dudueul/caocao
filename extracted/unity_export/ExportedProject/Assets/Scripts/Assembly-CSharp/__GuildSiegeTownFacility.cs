using System;
using System.Collections.Generic;

[Serializable]
public class __GuildSiegeTownFacility
{
	public string name;

	public int id;

	public int facilityType;

	public int slotType;

	public int linkedFacility;

	public string thumbnail;

	public int canReinforce;

	public int captureEffect;

	public int battleMapId;

	public int needGuildLv;

	public int costType;

	public int costValue;

	public int type0;

	public int reward0;

	public int type1;

	public int reward1;

	public string desc;

	public string caputreDesc;

	public List<int> byLv;

	public void CopyTo(GuildSiegeTownFacility dest)
	{
	}

	public void Reset()
	{
	}
}
