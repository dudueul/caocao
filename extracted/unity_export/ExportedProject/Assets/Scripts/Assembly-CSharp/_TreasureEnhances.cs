using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TreasureEnhances : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TreasureEnhance> data;

	public static _TreasureEnhances Load()
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
