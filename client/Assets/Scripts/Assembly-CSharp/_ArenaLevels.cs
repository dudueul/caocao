using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaLevels : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaLevel> data;

	public static _ArenaLevels Load()
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
