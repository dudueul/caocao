using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Passives : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Passive> data;

	public static _Passives Load()
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
