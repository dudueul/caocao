using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarTileCorpsCosts : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarTileCorpsCost> data;

	public static _GuildWarTileCorpsCosts Load()
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
