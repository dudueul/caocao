using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RecommendShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RecommendShop> data;

	public static _RecommendShops Load()
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
