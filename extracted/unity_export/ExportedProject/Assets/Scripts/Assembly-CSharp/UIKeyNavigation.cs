using UnityEngine;

public class UIKeyNavigation : MonoBehaviour
{
	public enum Constraint
	{
		None = 0,
		Vertical = 1,
		Horizontal = 2,
		Explicit = 3
	}

	public static BetterList<UIKeyNavigation> list;

	public Constraint constraint;

	public GameObject onUp;

	public GameObject onDown;

	public GameObject onLeft;

	public GameObject onRight;

	public GameObject onClick;

	public bool startsSelected;

	protected virtual void OnEnable()
	{
	}

	protected virtual void OnDisable()
	{
	}

	protected GameObject GetLeft()
	{
		return null;
	}

	private GameObject GetRight()
	{
		return null;
	}

	protected GameObject GetUp()
	{
		return null;
	}

	protected GameObject GetDown()
	{
		return null;
	}

	protected GameObject Get(Vector3 myDir, bool horizontal)
	{
		return null;
	}

	protected static Vector3 GetCenter(GameObject go)
	{
		return default(Vector3);
	}

	protected virtual void OnKey(KeyCode key)
	{
	}

	protected virtual void OnClick()
	{
	}
}
