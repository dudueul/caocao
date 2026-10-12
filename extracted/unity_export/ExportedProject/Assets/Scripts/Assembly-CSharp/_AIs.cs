using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _AIs : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__AI> data;

	public static _AIs Load()
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
