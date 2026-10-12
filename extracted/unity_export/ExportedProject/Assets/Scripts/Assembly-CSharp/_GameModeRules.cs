using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GameModeRules : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GameModeRule> data;

	public static _GameModeRules Load()
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
