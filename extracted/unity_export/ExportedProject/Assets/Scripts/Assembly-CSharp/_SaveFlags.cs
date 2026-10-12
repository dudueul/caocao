using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SaveFlags : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SaveFlag> data;

	public static _SaveFlags Load()
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
