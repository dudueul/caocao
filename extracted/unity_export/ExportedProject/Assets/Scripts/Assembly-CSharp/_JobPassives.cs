using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _JobPassives : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__JobPassive> data;

	public static _JobPassives Load()
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
