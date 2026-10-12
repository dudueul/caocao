using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SecretShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SecretShop> data;

	public static _SecretShops Load()
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
