using System;
using System.Collections.Generic;

[Serializable]
public class __ArenaSynergyList
{
	public int id;

	public int prevSynergy;

	public int nextSynergy;

	public List<int> synergy;

	public List<int> jobStyleTarget;

	public List<int> synergyTarget;

	public List<int> passive;

	public void CopyTo(ArenaSynergyList dest)
	{
	}

	public void Reset()
	{
	}
}
