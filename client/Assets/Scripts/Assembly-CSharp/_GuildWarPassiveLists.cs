using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarPassiveLists : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarPassiveList> data;

	public static _GuildWarPassiveLists Load()
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
