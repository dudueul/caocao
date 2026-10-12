using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _TicketShops : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__TicketShop> data;

	public static _TicketShops Load()
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
