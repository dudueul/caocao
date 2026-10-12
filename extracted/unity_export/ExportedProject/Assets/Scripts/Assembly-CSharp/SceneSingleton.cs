using UnityEngine;

public class SceneSingleton<T> : MonoBehaviour where T : MonoBehaviour
{
	private static readonly string ms_tagName;

	private static T ms_instance;

	private static string ms_destroyedScene;

	public static T instance
	{
		get
		{
			return null;
		}
	}

	public static bool hasInstance
	{
		get
		{
			return false;
		}
	}

	private static string GetName()
	{
		return null;
	}

	protected virtual void OnDestroy()
	{
	}

	protected void OnApplicationQuit()
	{
	}
}
