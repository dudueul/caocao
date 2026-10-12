using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Storybooks : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Storybook> data;

	public static _Storybooks Load()
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
