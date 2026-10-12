using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildSiegeCommanders : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildSiegeCommander> data;

	public static _GuildSiegeCommanders Load()
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
