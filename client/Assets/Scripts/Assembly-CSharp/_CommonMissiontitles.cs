using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _CommonMissiontitles : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__CommonMissiontitle> data;

	public static _CommonMissiontitles Load()
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
