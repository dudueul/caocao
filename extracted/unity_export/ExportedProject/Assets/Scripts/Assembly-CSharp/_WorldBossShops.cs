using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _WorldBossShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__WorldBossShop> data;

	public static _WorldBossShops Load()
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
