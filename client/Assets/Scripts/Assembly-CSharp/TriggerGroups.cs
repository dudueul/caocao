using System;
using System.Collections.Generic;

[Serializable]
public class TriggerGroups
{
	public string displayName;

	public EnumData.DamageEffectType damageEffectType;

	public int id;

	public List<TriggerGroup> groups;
}
