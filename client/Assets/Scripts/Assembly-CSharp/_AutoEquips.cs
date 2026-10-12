using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _AutoEquips : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__AutoEquip> data;

	public static _AutoEquips Load()
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
