using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _BattleMaps : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__BattleMap> data;

	public static _BattleMaps Load()
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
