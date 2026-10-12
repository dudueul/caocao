using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TimeBoxOrders : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TimeBoxOrder> data;

	public static _TimeBoxOrders Load()
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
