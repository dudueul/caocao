using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TradeShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TradeShop> data;

	public static _TradeShops Load()
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
