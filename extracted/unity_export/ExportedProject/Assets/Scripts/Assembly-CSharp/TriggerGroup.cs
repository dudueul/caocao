using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class TriggerGroup
{
	[HideInInspector]
	public string triggerName;

	public List<tk2dCustomTrigger> triggers;
}
