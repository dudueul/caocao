using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarPings : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarPing> data;

	public static _GuildWarPings Load()
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
