using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarTileTypes : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarTileType> data;

	public static _GuildWarTileTypes Load()
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
