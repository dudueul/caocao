using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarPassiveAttributes : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarPassiveAttribute> data;

	public static _GuildWarPassiveAttributes Load()
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
