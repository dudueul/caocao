using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildPresents : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildPresent> data;

	public static _GuildPresents Load()
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
