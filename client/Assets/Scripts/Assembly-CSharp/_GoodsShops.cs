using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GoodsShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GoodsShop> data;

	public static _GoodsShops Load()
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
