using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _Mails : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__Mail> data;

	public static _Mails Load()
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
