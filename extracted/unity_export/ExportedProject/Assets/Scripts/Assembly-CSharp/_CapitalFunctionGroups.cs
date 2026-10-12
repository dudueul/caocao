using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _CapitalFunctionGroups : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__CapitalFunctionGroup> data;

	public static _CapitalFunctionGroups Load()
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
