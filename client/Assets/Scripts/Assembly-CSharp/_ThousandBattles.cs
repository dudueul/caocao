using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ThousandBattles : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ThousandBattle> data;

	public static _ThousandBattles Load()
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
