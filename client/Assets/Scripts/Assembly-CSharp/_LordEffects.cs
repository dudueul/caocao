using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _LordEffects : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__LordEffect> data;

	public static _LordEffects Load()
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
