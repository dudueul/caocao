using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Worldmaps : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Worldmap> data;

	public static _Worldmaps Load()
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
