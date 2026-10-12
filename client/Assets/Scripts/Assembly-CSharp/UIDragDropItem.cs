using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class UIDragDropItem : MonoBehaviour
{
	public enum Restriction
	{
		None = 0,
		Horizontal = 1,
		Vertical = 2,
		PressAndHold = 3
	}

	public Restriction restriction;

	public bool cloneOnDrag;

	[HideInInspector]
	public float pressAndHoldDelay;

	public bool interactable;

	[NonSerialized]
	protected Transform mTrans;

	[NonSerialized]
	protected Transform mParent;

	[NonSerialized]
	protected Collider mCollider;

	[NonSerialized]
	protected Collider2D mCollider2D;

	[NonSerialized]
	protected UIButton mButton;

	[NonSerialized]
	protected UIRoot mRoot;

	[NonSerialized]
	protected UIGrid mGrid;

	[NonSerialized]
	protected UITable mTable;

	[NonSerialized]
	protected float mDragStartTime;

	[NonSerialized]
	protected UIDragScrollView mDragScrollView;

	[NonSerialized]
	protected bool mPressed;

	[NonSerialized]
	protected bool mDragging;

	[NonSerialized]
	protected UICamera.MouseOrTouch mTouch;

	protected virtual void Start()
	{
	}

	protected virtual void OnPress(bool isPressed)
	{
	}

	protected virtual void Update()
	{
	}

	protected virtual void OnDragStart()
	{
	}

	protected virtual void StartDragging()
	{
	}

	protected virtual void OnDrag(Vector2 delta)
	{
	}

	protected virtual void OnDragEnd()
	{
	}

	public void StopDragging(GameObject go)
	{
	}

	protected virtual void OnDragDropStart()
	{
	}

	protected virtual void OnDragDropMove(Vector2 delta)
	{
	}

	protected virtual void OnDragDropRelease(GameObject surface)
	{
	}

	protected virtual void OnDragDropEnd()
	{
	}

	[DebuggerHidden]
	protected IEnumerator EnableDragScrollView()
	{
		return null;
	}
}
