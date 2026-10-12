using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Leagues : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__League> data;

	public static _Leagues Load()
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
