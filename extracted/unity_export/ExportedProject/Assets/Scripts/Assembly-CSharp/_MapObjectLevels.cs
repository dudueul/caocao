using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _MapObjectLevels : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__MapObjectLevel> data;

	public static _MapObjectLevels Load()
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
