using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _States : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__State> data;

	public static _States Load()
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
