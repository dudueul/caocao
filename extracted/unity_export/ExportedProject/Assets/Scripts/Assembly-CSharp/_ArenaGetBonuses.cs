using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaGetBonuses : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaGetBonus> data;

	public static _ArenaGetBonuses Load()
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
