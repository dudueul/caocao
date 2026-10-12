using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SoundGroups : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SoundGroup> data;

	public static _SoundGroups Load()
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
