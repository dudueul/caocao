using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildSiegeNPCEquipSettings : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildSiegeNPCEquipSetting> data;

	public static _GuildSiegeNPCEquipSettings Load()
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
