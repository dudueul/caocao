using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _RankUpGifts : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__RankUpGift> data;

	public static _RankUpGifts Load()
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
