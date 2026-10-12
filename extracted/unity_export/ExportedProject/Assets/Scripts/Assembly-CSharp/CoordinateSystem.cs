using UnityEngine;
using UnityEngine.SceneManagement;

[ExecuteInEditMode]
public class CoordinateSystem : Singleton<CoordinateSystem>
{
	private static ICoordinateSystem ms_coordinateSystem;

	protected override void Awake()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnLevelFinishedLoading(Scene scene, LoadSceneMode mode)
	{
	}

	public void CreateOrReplaceCoordinateSystem()
	{
	}

	public static IntVector2 convertWorldPosToGridPos(Vector2 pos)
	{
		return default(IntVector2);
	}

	public static Vector2 convertGridPosToWorldPos(IntVector2 gridPos)
	{
		return default(Vector2);
	}
}
