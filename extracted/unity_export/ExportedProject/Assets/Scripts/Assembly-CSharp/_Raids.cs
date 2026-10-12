using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Raids : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Raid> data;

	public static _Raids Load()
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
