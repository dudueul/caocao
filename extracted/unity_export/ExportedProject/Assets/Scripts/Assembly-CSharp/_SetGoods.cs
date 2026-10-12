using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SetGoods : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SetGood> data;

	public static _SetGoods Load()
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
