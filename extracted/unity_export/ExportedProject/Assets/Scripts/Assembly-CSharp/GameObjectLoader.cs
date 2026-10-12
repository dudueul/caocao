using System;
using System.Collections.Generic;
using UnityEngine;

public class GameObjectLoader : MonoBehaviour
{
	[Serializable]
	public class PrefabInfo
	{
		[ObjectWeakRef]
		public string prefabPath;

		public Transform parent;
	}

	public List<PrefabInfo> prefabInfos;

	private void Awake()
	{
	}
}
