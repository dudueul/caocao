using UnityEngine;

public static class tk2dUtil
{
	private static string label;

	private static bool undoEnabled;

	public static bool UndoEnabled
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public static void BeginGroup(string name)
	{
	}

	public static void EndGroup()
	{
	}

	public static void DestroyImmediate(Object obj)
	{
	}

	public static GameObject CreateGameObject(string name)
	{
		return null;
	}

	public static Mesh CreateMesh()
	{
		return null;
	}

	public static T AddComponent<T>(GameObject go) where T : Component
	{
		return null;
	}

	public static void SetActive(GameObject go, bool active)
	{
	}

	public static void SetTransformParent(Transform t, Transform parent)
	{
	}
}
