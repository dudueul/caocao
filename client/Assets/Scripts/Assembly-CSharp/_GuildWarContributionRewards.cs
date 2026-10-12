using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarContributionRewards : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarContributionReward> data;

	public static _GuildWarContributionRewards Load()
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
