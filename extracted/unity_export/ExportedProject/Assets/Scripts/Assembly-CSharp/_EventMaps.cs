using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _EventMaps : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__EventMap> data;

	public static _EventMaps Load()
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
