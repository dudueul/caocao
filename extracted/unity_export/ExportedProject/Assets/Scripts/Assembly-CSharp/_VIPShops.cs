using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _VIPShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__VIPShop> data;

	public static _VIPShops Load()
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
