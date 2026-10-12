using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarWorlds : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarWorld> data;

	public static _GuildWarWorlds Load()
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
