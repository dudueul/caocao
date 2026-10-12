using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _NPCNames : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__NPCName> data;

	public static _NPCNames Load()
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
