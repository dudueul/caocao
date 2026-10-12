using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _DailyGifts : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__DailyGift> data;

	public static _DailyGifts Load()
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
