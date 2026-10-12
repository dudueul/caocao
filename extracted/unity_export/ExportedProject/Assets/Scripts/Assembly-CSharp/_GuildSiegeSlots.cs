using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildSiegeSlots : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildSiegeSlot> data;

	public static _GuildSiegeSlots Load()
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
