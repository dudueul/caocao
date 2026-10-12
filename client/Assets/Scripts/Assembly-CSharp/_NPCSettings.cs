using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _NPCSettings : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__NPCSetting> data;

	public static _NPCSettings Load()
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
