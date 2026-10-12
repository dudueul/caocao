using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _UnitPassives : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__UnitPassive> data;

	public static _UnitPassives Load()
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
