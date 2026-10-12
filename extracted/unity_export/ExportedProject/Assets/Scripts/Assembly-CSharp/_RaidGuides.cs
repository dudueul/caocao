using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RaidGuides : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RaidGuide> data;

	public static _RaidGuides Load()
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
