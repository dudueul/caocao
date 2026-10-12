using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _BattleFieldNpcGroups : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__BattleFieldNpcGroup> data;

	public static _BattleFieldNpcGroups Load()
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
