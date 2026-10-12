using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarCorpsJobs : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarCorpsJob> data;

	public static _GuildWarCorpsJobs Load()
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
