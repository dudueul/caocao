using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaThemes : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaTheme> data;

	public static _ArenaThemes Load()
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
