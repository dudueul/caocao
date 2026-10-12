using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _LinkedGoodses : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__LinkedGoods> data;

	public static _LinkedGoodses Load()
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
