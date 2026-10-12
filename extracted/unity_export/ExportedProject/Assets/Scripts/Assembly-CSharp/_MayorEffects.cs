using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _MayorEffects : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__MayorEffect> data;

	public static _MayorEffects Load()
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
