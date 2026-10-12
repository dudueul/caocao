using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RelationMissions : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RelationMission> data;

	public static _RelationMissions Load()
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
