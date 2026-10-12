using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GuildSiegeTownFacilityByLVs : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GuildSiegeTownFacilityByLV> data;

	public static _GuildSiegeTownFacilityByLVs Load()
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
