using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RunePassives : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RunePassive> data;

	public static _RunePassives Load()
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
