using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TreasureEnhancePassives : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TreasureEnhancePassive> data;

	public static _TreasureEnhancePassives Load()
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
