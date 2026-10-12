using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Items : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Item> data;

	public static _Items Load()
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
