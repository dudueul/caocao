using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ThousandSoldiers : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ThousandSoldier> data;

	public static _ThousandSoldiers Load()
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
