using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildShop> data;

	public static _GuildShops Load()
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
