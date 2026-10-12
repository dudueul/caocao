using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Guilds : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Guild> data;

	public static _Guilds Load()
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
