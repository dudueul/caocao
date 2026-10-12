using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarTownRewards : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarTownReward> data;

	public static _GuildWarTownRewards Load()
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
