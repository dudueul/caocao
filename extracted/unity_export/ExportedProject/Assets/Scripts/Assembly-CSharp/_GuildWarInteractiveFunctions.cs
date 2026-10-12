using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarInteractiveFunctions : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarInteractiveFunction> data;

	public static _GuildWarInteractiveFunctions Load()
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
