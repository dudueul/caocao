using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _BattleFormationFuncs : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__BattleFormationFunc> data;

	public static _BattleFormationFuncs Load()
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
