using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _MapObjects : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__MapObject> data;

	public static _MapObjects Load()
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
