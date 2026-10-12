using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _UnitRankSeasons : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__UnitRankSeason> data;

	public static _UnitRankSeasons Load()
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
