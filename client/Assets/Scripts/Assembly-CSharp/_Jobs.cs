using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Jobs : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Job> data;

	public static _Jobs Load()
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
