using UnityEngine;

public class UIDraggableCamera : MonoBehaviour
{
	public Transform rootForBounds;

	public Vector2 scale;

	public float scrollWheelFactor;

	public UIDragObject.DragEffect dragEffect;

	public bool smoothDragStart;

	public float momentumAmount;

	private Camera mCam;

	private Transform mTrans;

	private bool mPressed;

	private Vector2 mMomentum;

	private Bounds mBounds;

	private float mScroll;

	private UIRoot mRoot;

	private bool mDragStarted;

	public Vector2 currentMomentum
	{
		get
		{
			return default(Vector2);
		}
		set
		{
		}
	}

	private void Start()
	{
	}

	private Vector3 CalculateConstrainOffset()
	{
		return default(Vector3);
	}

	public bool ConstrainToBounds(bool immediate)
	{
		return false;
	}

	public void Press(bool isPressed)
	{
	}

	public void Drag(Vector2 delta)
	{
	}

	public void Scroll(float delta)
	{
	}

	private void Update()
	{
	}
}
