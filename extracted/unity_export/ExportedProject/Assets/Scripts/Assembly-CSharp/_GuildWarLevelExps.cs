using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarLevelExps : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarLevelExp> data;

	public static _GuildWarLevelExps Load()
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
