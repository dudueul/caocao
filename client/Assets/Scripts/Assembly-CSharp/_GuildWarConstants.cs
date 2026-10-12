using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarConstants : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarConstant> data;

	public static _GuildWarConstants Load()
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
