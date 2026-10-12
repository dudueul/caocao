using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Rankings : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Ranking> data;

	public static _Rankings Load()
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
