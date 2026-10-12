using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _EventDailyGifts : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__EventDailyGift> data;

	public static _EventDailyGifts Load()
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
