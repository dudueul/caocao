using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class UIComponentsHolder : MonoBehaviour
{
	[Serializable]
	public class PathInfo
	{
		public string name;

		[ObjectWeakRef]
		public string path;
	}

	public static UIComponentsHolder instance;

	public List<PathInfo> pathInfos;

	private Dictionary<string, string> m_name2path;

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	[DebuggerHidden]
	public IEnumerator LoadPrefabAsync(string name)
	{
		return null;
	}

	public GameObject GetUIComponent(string name)
	{
		return null;
	}
}
