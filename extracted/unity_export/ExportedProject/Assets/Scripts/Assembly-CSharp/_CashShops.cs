using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _CashShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__CashShop> data;

	public static _CashShops Load()
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
