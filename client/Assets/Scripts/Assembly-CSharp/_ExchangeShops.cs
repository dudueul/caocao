using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ExchangeShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ExchangeShop> data;

	public static _ExchangeShops Load()
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
