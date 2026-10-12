using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaSynergies : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaSynergy> data;

	public static _ArenaSynergies Load()
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
