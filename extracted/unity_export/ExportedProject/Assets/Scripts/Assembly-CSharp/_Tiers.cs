using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Tiers : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Tier> data;

	public static _Tiers Load()
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
