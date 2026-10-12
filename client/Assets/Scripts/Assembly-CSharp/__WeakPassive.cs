using System;
using System.Collections.Generic;

[Serializable]
public class __WeakPassive
{
	public string name;

	public int id;

	public List<string> weakAttackType;

	public List<string> weakOpenType;

	public int conditionHarden;

	public int conditionSoften;

	public int maxArmor;

	public int addArmor;

	public int weakDamageRate;

	public int strongDamageRate;

	public int softenDuration;

	public int softenDamageRate;

	public void CopyTo(WeakPassive dest)
	{
	}

	public void Reset()
	{
	}
}
