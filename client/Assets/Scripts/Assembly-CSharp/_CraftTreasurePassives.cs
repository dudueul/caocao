using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _CraftTreasurePassives : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__CraftTreasurePassive> data;

	public static _CraftTreasurePassives Load()
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
