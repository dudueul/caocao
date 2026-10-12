using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarTiles : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarTile> data;

	public static _GuildWarTiles Load()
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
