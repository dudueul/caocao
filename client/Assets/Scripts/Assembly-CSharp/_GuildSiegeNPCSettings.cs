using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildSiegeNPCSettings : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildSiegeNPCSetting> data;

	public static _GuildSiegeNPCSettings Load()
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
