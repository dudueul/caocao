using UnityEngine;

public class UIDragResize : MonoBehaviour
{
	public UIWidget target;

	public UIWidget.Pivot pivot;

	public int minWidth;

	public int minHeight;

	public int maxWidth;

	public int maxHeight;

	private Plane mPlane;

	private Vector3 mRayPos;

	private Vector3 mLocalPos;

	private int mWidth;

	private int mHeight;

	private bool mDragging;

	private void OnDragStart()
	{
	}

	private void OnDrag(Vector2 delta)
	{
	}

	private void OnDragEnd()
	{
	}
}
