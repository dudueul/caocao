using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _StoryMissions : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__StoryMission> data;

	public static _StoryMissions Load()
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
