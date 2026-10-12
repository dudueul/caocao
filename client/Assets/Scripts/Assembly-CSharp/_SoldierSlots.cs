using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _SoldierSlots : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__SoldierSlot> data;

	public static _SoldierSlots Load()
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
