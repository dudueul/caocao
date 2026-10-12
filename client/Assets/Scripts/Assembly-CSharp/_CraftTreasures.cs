using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _CraftTreasures : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__CraftTreasure> data;

	public static _CraftTreasures Load()
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
