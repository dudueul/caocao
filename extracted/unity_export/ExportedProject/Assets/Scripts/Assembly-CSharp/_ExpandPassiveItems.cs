using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ExpandPassiveItems : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ExpandPassiveItem> data;

	public static _ExpandPassiveItems Load()
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
