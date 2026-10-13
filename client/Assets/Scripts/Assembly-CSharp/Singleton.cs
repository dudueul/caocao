// [RE] Rebuilt singleton (TypeDefIndex pattern `class X : Singleton<T>` — 44
// singletons in dump.cs). Standard Unity MonoBehaviour singleton with the
// original's safety flags (tagName/destroyed/quitting/isAddingComponent).
using UnityEngine;

public class Singleton<T> : MonoBehaviour where T : MonoBehaviour
{
	private static readonly string ms_tagName;

	private static T ms_instance;

	private static bool ms_destroyed;

	private static bool ms_applicationIsQuitting;

	private static bool ms_isAddingComponent;

	private static GameObject GetRootObject()
	{
		// [RE] original groups singletons under one DontDestroyOnLoad root
		var go = GameObject.Find("/" + GetName());
		if (go == null)
		{
			go = new GameObject("/" + GetName());
			DontDestroyOnLoad(go);
		}
		return go;
	}

	public static T instance
	{
		get
		{
			if (ms_destroyed || ms_applicationIsQuitting)
				return null;
			if (ms_instance != null)
				return ms_instance;
			ms_instance = UnityEngine.Object.FindAnyObjectByType<T>();
			if (ms_instance != null)
				return ms_instance;
			if (ms_isAddingComponent)
				return null;
			ms_isAddingComponent = true;
			var go = GetRootObject();
			ms_instance = go.AddComponent<T>();
			ms_isAddingComponent = false;
			return ms_instance;
		}
	}

	public static bool HasInstance
	{
		get
		{
			return ms_instance != null && !ms_destroyed;
		}
	}

	protected virtual void Awake()
	{
		if (ms_instance != null && ms_instance != this)
		{
			Destroy(gameObject);
			return;
		}
		ms_instance = (T)(object)this;
		DontDestroyOnLoad(gameObject);
	}

	private static string GetName()
	{
		return typeof(T).Name + "s";   // [RE] shared root; original used tagName
	}

	protected virtual void OnDestroy()
	{
		if (ms_instance == (object)this)
		{
			ms_instance = null;
			ms_destroyed = true;
		}
	}

	protected virtual void OnApplicationQuit()
	{
		ms_applicationIsQuitting = true;
	}

	public static void DestroyInstance()
	{
		if (ms_instance != null)
		{
			Destroy(ms_instance.gameObject);
			ms_instance = null;
		}
		ms_destroyed = false;
	}
}
