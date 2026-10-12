using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaGetPassives : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaGetPassive> data;

	public static _ArenaGetPassives Load()
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
