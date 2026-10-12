using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TownFunctionGroups : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TownFunctionGroup> data;

	public static _TownFunctionGroups Load()
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
