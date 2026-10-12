using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarTileEffects : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarTileEffect> data;

	public static _GuildWarTileEffects Load()
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
