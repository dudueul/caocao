using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _NewBossSettings : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__NewBossSetting> data;

	public static _NewBossSettings Load()
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
