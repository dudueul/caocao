using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SetItems : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SetItem> data;

	public static _SetItems Load()
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
