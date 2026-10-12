using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TileJobDefenses : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TileJobDefense> data;

	public static _TileJobDefenses Load()
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
