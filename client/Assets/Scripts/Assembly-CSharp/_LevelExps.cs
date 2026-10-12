using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _LevelExps : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__LevelExp> data;

	public static _LevelExps Load()
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
