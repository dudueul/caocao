using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class _EventCommands : ScriptableObject
{
	public bool isMatrix;

	public string key;

	public List<__EventCommand> data;

	public static _EventCommands Load()
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
