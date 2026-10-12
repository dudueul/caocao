using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Stickers : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Sticker> data;

	public static _Stickers Load()
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
