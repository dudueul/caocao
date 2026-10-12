using System;
using System.Collections.Generic;

[Serializable]
public class __AISet
{
	public string name;

	public int id;

	public int hpAI1;

	public int hpAI2;

	public int mpAI;

	public List<int> techLv;

	public List<int> tendencyParts;

	public List<int> spellParts;

	public List<int> recoverTendency;

	public List<int> recoverSpell;

	public List<int> hpRecoverTendency;

	public List<int> hpRecoverSpell;

	public List<int> mpRecoverTendency;

	public List<int> mpRecoverSpell;

	public void CopyTo(AISet dest)
	{
	}

	public void Reset()
	{
	}
}
