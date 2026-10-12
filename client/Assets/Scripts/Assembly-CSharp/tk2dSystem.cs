using System;
using UnityEngine;

public class tk2dSystem : ScriptableObject
{
	public const string guidPrefix = "tk2d/tk2d_";

	public const string assetName = "tk2d/tk2dSystem";

	public const string assetFileName = "tk2dSystem.asset";

	[NonSerialized]
	public tk2dAssetPlatform[] assetPlatforms;

	private static tk2dSystem _inst;

	private static string currentPlatform;

	[SerializeField]
	private tk2dResourceTocEntry[] allResourceEntries;

	public static tk2dSystem inst
	{
		get
		{
			return null;
		}
	}

	public static tk2dSystem inst_NoCreate
	{
		get
		{
			return null;
		}
	}

	public static string CurrentPlatform
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static bool OverrideBuildMaterial
	{
		get
		{
			return false;
		}
	}

	private tk2dSystem()
	{
	}

	public static tk2dAssetPlatform GetAssetPlatform(string platform)
	{
		return null;
	}

	private T LoadResourceByGUIDImpl<T>(string guid) where T : UnityEngine.Object
	{
		return null;
	}

	private T LoadResourceByNameImpl<T>(string name) where T : UnityEngine.Object
	{
		return null;
	}

	public static T LoadResourceByGUID<T>(string guid) where T : UnityEngine.Object
	{
		return null;
	}

	public static T LoadResourceByName<T>(string guid) where T : UnityEngine.Object
	{
		return null;
	}
}
