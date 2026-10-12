using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _GameModeRuleItems : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__GameModeRuleItem> data;

	public static _GameModeRuleItems Load()
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
