using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Missions : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Mission> data;

	public static _Missions Load()
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
