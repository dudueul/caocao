using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildSupports : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildSupport> data;

	public static _GuildSupports Load()
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
