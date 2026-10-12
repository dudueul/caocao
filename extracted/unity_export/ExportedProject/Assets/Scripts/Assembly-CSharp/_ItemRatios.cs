using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ItemRatios : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ItemRatio> data;

	public static _ItemRatios Load()
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
