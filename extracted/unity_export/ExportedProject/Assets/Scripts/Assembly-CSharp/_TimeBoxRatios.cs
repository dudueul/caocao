using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TimeBoxRatios : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TimeBoxRatio> data;

	public static _TimeBoxRatios Load()
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
