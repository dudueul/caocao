using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _UnitRanks : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__UnitRank> data;

	public static _UnitRanks Load()
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
