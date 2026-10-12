using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _PassiveLists : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__PassiveList> data;

	public static _PassiveLists Load()
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
