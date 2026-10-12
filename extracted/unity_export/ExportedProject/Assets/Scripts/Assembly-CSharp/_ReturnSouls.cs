using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ReturnSouls : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ReturnSoul> data;

	public static _ReturnSouls Load()
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
