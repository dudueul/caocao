using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildWarBuildingBattleDatas : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildWarBuildingBattleData> data;

	public static _GuildWarBuildingBattleDatas Load()
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
