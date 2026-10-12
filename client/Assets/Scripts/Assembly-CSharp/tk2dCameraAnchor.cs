using UnityEngine;

[ExecuteInEditMode]
public class tk2dCameraAnchor : MonoBehaviour
{
	[SerializeField]
	private int anchor;

	[SerializeField]
	private tk2dBaseSprite.Anchor _anchorPoint;

	[SerializeField]
	private bool anchorToNativeBounds;

	[SerializeField]
	private Vector2 offset;

	[SerializeField]
	private tk2dCamera tk2dCamera;

	[SerializeField]
	private Camera _anchorCamera;

	private Camera _anchorCameraCached;

	private tk2dCamera _anchorTk2dCamera;

	private Transform _myTransform;

	public tk2dBaseSprite.Anchor AnchorPoint
	{
		get
		{
			return tk2dBaseSprite.Anchor.LowerLeft;
		}
		set
		{
		}
	}

	public Vector2 AnchorOffsetPixels
	{
		get
		{
			return default(Vector2);
		}
		set
		{
		}
	}

	public bool AnchorToNativeBounds
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public Camera AnchorCamera
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private tk2dCamera AnchorTk2dCamera
	{
		get
		{
			return null;
		}
	}

	private Transform myTransform
	{
		get
		{
			return null;
		}
	}

	private void Start()
	{
	}

	private void UpdateTransform()
	{
	}

	public void ForceUpdateTransform()
	{
	}

	private void LateUpdate()
	{
	}
}
