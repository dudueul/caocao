using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _MarketShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__MarketShop> data;

	public static _MarketShops Load()
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
