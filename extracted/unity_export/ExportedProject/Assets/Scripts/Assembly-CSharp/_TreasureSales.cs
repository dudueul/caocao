using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TreasureSales : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TreasureSale> data;

	public static _TreasureSales Load()
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
