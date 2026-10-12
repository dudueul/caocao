using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarSiegeWeapons : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarSiegeWeapon> data;

	public static _GuildWarSiegeWeapons Load()
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
