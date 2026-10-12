using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWeeklyMissions : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWeeklyMission> data;

	public static _GuildWeeklyMissions Load()
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
