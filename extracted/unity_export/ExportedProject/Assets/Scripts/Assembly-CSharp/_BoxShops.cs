using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _BoxShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__BoxShop> data;

	public static _BoxShops Load()
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
