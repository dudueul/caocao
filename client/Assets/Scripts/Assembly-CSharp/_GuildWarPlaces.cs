using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarPlaces : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarPlace> data;

	public static _GuildWarPlaces Load()
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
