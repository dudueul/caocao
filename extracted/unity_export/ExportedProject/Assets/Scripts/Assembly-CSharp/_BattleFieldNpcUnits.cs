using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _BattleFieldNpcUnits : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__BattleFieldNpcUnit> data;

	public static _BattleFieldNpcUnits Load()
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
