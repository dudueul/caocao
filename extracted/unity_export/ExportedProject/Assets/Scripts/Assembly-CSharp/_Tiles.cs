using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Tiles : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Tile> data;

	public static _Tiles Load()
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
