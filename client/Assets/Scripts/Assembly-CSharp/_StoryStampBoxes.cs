using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _StoryStampBoxes : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__StoryStampBox> data;

	public static _StoryStampBoxes Load()
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
