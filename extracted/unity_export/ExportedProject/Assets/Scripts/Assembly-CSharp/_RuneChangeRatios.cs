using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RuneChangeRatios : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RuneChangeRatio> data;

	public static _RuneChangeRatios Load()
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
