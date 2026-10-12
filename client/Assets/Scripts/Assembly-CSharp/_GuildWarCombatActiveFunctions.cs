using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarCombatActiveFunctions : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarCombatActiveFunction> data;

	public static _GuildWarCombatActiveFunctions Load()
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
