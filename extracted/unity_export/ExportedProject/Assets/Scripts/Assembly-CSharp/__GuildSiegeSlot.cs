using System;
using System.Collections.Generic;

[Serializable]
public class __GuildSiegeSlot
{
	public int id;

	public int slotNo;

	public int slotType;

	public List<int> linkedSlot;

	public int defaultFacility;

	public void CopyTo(GuildSiegeSlot dest)
	{
	}

	public void Reset()
	{
	}
}
