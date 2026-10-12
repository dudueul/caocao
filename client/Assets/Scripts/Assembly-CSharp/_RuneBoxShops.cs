using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RuneBoxShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RuneBoxShop> data;

	public static _RuneBoxShops Load()
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
