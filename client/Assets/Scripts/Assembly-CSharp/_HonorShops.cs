using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _HonorShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__HonorShop> data;

	public static _HonorShops Load()
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
