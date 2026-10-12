using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ShopOrders : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ShopOrder> data;

	public static _ShopOrders Load()
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
