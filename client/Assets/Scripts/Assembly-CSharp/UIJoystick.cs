using UnityEngine;

public class UIJoystick : MonoBehaviour
{
	private Transform target;

	public Vector3 scale;

	public Vector2 range;

	public float deadZone;

	public bool circularPadConstraint;

	public float springBackSpeed;

	public Vector2 padPosition;

	public float padAngle;

	public Vector3 padPositionAndAngle;

	private Vector3 mStartPos;

	private Vector3 mStartLocalPos;

	private Plane mPlane;

	private Vector3 mLastPos;

	private bool mPressed;

	private Vector3 totalOffset;

	private Vector3 startOffset;

	private Vector3 totalWorldOffset;

	private bool mDragStarted;

	private Vector2 mDragStartOffset;

	private bool started;

	private void Start()
	{
	}

	private void OnPress(bool pressed)
	{
	}

	private void Update()
	{
	}

	private static bool Vector3AlmostEquals(Vector3 target, Vector3 second, float sqrMagniturePrecision)
	{
		return false;
	}

	private void OnDrag(Vector2 delta)
	{
	}

	private void LateUpdate()
	{
	}
}
