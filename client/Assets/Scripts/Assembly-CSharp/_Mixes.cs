using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Mixes : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Mix> data;

	public static _Mixes Load()
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
