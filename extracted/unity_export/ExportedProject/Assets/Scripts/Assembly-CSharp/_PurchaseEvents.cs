using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _PurchaseEvents : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__PurchaseEvent> data;

	public static _PurchaseEvents Load()
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
