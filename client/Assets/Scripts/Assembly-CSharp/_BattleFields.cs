using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _BattleFields : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__BattleField> data;

	public static _BattleFields Load()
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
