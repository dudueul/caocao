using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _AITendencies : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__AITendency> data;

	public static _AITendencies Load()
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
