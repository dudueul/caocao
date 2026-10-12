using UnityEngine;

public class Singleton<T> : MonoBehaviour where T : MonoBehaviour
{
	private static readonly string ms_tagName;

	private static T ms_instance;

	private static bool ms_destroyed;

	private static bool ms_applicationIsQuitting;

	private static bool ms_isAddingComponent;

	public static T instance
	{
		get
		{
			return null;
		}
	}

	public static bool HasInstance
	{
		get
		{
			return false;
		}
	}

	protected virtual void Awake()
	{
	}

	private static string GetName()
	{
		return null;
	}

	protected virtual void OnDestroy()
	{
	}

	protected virtual void OnApplicationQuit()
	{
	}

	public static void DestroyInstance()
	{
	}
}
