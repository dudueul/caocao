using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _ArenaPassiveLists : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__ArenaPassiveList> data;

	public static _ArenaPassiveLists Load()
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
