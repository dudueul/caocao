using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaDeckSettings : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaDeckSetting> data;

	public static _ArenaDeckSettings Load()
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
