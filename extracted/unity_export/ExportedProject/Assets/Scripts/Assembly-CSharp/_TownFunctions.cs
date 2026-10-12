using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TownFunctions : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TownFunction> data;

	public static _TownFunctions Load()
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
