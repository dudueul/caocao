using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _WeakPassives : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__WeakPassive> data;

	public static _WeakPassives Load()
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
