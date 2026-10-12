using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _BattleFieldNpcGroupTypes : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__BattleFieldNpcGroupType> data;

	public static _BattleFieldNpcGroupTypes Load()
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
