using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _NewThousandBattles : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__NewThousandBattle> data;

	public static _NewThousandBattles Load()
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
