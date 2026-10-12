using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaGetTreasures : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaGetTreasure> data;

	public static _ArenaGetTreasures Load()
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
