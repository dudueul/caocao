using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _StickerSets : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__StickerSet> data;

	public static _StickerSets Load()
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
