using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Towns : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Town> data;

	public static _Towns Load()
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
