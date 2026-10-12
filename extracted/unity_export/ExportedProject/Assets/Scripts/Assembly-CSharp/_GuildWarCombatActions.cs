using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarCombatActions : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarCombatAction> data;

	public static _GuildWarCombatActions Load()
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
