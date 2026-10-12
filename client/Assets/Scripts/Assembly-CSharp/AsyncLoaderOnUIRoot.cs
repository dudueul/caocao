using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class AsyncLoaderOnUIRoot : MonoBehaviour
{
	[Serializable]
	public class AsyncLoadInfo
	{
		public string name;

		public bool instantiate;

		[ObjectWeakRef]
		public string path;

		public Transform otherParent;
	}

	public static AsyncLoaderOnUIRoot instance;

	public List<AsyncLoadInfo> asyncLoadInfos;

	public int asyncLoadInfoCount
	{
		get
		{
			return 0;
		}
	}

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	[DebuggerHidden]
	public IEnumerator LoadAsync()
	{
		return null;
	}
}
