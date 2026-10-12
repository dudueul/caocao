using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _UnitRankPassives : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__UnitRankPassive> data;

	public static _UnitRankPassives Load()
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
