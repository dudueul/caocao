using System;
using System.Collections.Generic;
using UnityEngine;

public class PrefabPreloader : MonoBehaviour
{
	[Serializable]
	public class PreloadInfo
	{
		[ObjectWeakRef]
		public string path;
	}

	public static PrefabPreloader instance;

	public List<PreloadInfo> preloadInfosStep1;

	public List<PreloadInfo> preloadInfosStep2;

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	public void StartLoadingStep1()
	{
	}

	public void StartLoadingStep2()
	{
	}
}
