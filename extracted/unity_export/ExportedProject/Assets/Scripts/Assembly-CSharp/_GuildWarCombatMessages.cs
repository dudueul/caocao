using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarCombatMessages : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarCombatMessage> data;

	public static _GuildWarCombatMessages Load()
	{
		return null;
	}

	private void Reset()
	{
	}

	public void Destroy()
	{
	}
}
