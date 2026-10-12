using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarBuildings : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarBuilding> data;

	public static _GuildWarBuildings Load()
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
