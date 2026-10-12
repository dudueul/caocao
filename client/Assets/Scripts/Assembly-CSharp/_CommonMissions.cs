using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _CommonMissions : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__CommonMission> data;

	public static _CommonMissions Load()
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
