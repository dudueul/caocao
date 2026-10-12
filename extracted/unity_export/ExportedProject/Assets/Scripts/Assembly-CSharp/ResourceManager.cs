using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class ResourceManager : Singleton<ResourceManager>
{
	public enum BundleType
	{
		Raw = 0,
		Local = 1,
		Download = 2,
		StreamingAssets = 3
	}

	private class AsyncResourceInfo
	{
		public bool loaded { get; set; }

		public ResourceRequest resourceRequest { get; set; }
	}

	private Dictionary<string, string> m_bundlePath2Hash;

	private Dictionary<string, List<string>> m_bundleHash2dependentBundleHashes;

	private Dictionary<string, bool> m_bundleHash2Encrypted;

	private Dictionary<string, AssetBundle> m_bundleHash2Asset;

	private Dictionary<Type, Dictionary<string, UnityEngine.Object>> m_type2__path2obj__;

	private Dictionary<string, UnityEngine.Object[]> m_bundleHash2DependentObjs;

	private Dictionary<Type, string> m_type2DefaultExt;

	private Dictionary<string, List<int>> m_instanceIds;

	private IPatchProcessor m_patchProcessor;

	public string bundleVersion;

	private Dictionary<string, AsyncResourceInfo> m_path2preloadingPrefabs;

	public BundleType bundleType
	{
		get
		{
			return BundleType.Raw;
		}
	}

	private BundleType underlyingBundleType
	{
		get
		{
			return BundleType.Raw;
		}
	}

	public IPatchProcessor patchProcessor
	{
		get
		{
			return null;
		}
	}

	public string assetBundleVersion
	{
		get
		{
			return null;
		}
	}

	public bool isPreloading { get; set; }

	private ResourceManager()
	{
	}

	private void Update()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void UnloadAllResources(bool finalize)
	{
	}

	public void UnloadAssetBundle(string resourcePath, Type type)
	{
	}

	[DebuggerHidden]
	public IEnumerator UnloadUnusedAssets(bool finalize)
	{
		return null;
	}

	private void UpdateAsyncLoading()
	{
	}

	public void ClearPreloading()
	{
	}

	public int GetPreloadingCount()
	{
		return 0;
	}

	public void PreloadPrefabAsync(string resourcePath)
	{
	}

	public bool IsCachedResource(string resourcePath, Type resourceType)
	{
		return false;
	}

	private UnityEngine.Object GetLoadedResource(string resourcePath, Type resourceType)
	{
		return null;
	}

	private void RegisterLoadedResource(string resourcePath, Type resourceType, UnityEngine.Object resource)
	{
	}

	private UnityEngine.Object LoadResourceFromAssetBundle(string key, string resourcePath, Type resourceType, string extension = null)
	{
		return null;
	}

	private UnityEngine.Object LoadResourceFromResources(string key, string resourcePath, Type resourceType)
	{
		return null;
	}

	private UnityEngine.Object LoadResourceInternal(string key, string resourcePath, Type resourceType, string extension = null)
	{
		return null;
	}

	private bool IsBundleNameExist(string resourcePath, string extension)
	{
		return false;
	}

	public bool IsBundleExist(string resourcePath, Type resourceType)
	{
		return false;
	}

	public T LoadResource<T>(string resourcePath, string extension = null) where T : UnityEngine.Object
	{
		return null;
	}

	private void LoadDependencies(string bundleName)
	{
	}

	private AssetBundle LoadAssetBundleOptimized(string hash, bool cacheBundle)
	{
		return null;
	}

	private AssetBundle LoadAssetBundle(string hash, bool cacheBundle)
	{
		return null;
	}

	private string GetDefaultExt(Type type)
	{
		return null;
	}

	private string Path2Hash(string fileName)
	{
		return null;
	}

	private bool IsEncryptedAssetBundle(string hash)
	{
		return false;
	}

	public void MountPatchList()
	{
	}

	public void SetDependency()
	{
	}

	public string GetBundlePathFromAssetPath(string assetPath)
	{
		return null;
	}
}
