using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaAIs : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaAI> data;

	public static _ArenaAIs Load()
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
