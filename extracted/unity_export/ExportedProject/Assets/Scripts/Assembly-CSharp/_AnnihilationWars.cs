using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _AnnihilationWars : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__AnnihilationWar> data;

	public static _AnnihilationWars Load()
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
